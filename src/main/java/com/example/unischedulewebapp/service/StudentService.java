package com.example.unischedulewebapp.service;

import com.example.unischedulewebapp.auth.UserDetailsImpl;
import com.example.unischedulewebapp.auth.UserDetailsServiceImpl;
import com.example.unischedulewebapp.auth.exception.UserAlreadyExistsException;
import com.example.unischedulewebapp.exception.BadResourceException;
import com.example.unischedulewebapp.exception.ResourceAlreadyExistsException;
import com.example.unischedulewebapp.exception.ResourceNotFoundException;
import com.example.unischedulewebapp.model.AcademicProgram;
import com.example.unischedulewebapp.model.Student;
import com.example.unischedulewebapp.model.User;
import com.example.unischedulewebapp.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private final static String STUDENT_NOT_FOUND_MSG =
            "Student %s not found!";
    private final static String STUDENT_EXISTS_MSG =
            "Student %s already exists!";
    private final static String STUDENT_PROGRAM_NOT_FOUND_MSG =
            "Student is part of a non-existent program!";
    private final static String STUDENT_INVALID_YEAR_MSG =
            "Student's academic year is outside of their program's education period!";
    private static final String STUDENT_FAC_NUMBER_OVERWRITE_MSG =
            "Student's faculty number can't be overwritten!";

    private final StudentRepo studentRepo;
    private final UserService userService;
    private final AcademicProgramService programService;

    @Autowired
    public StudentService(StudentRepo studentRepo,
                          UserService userService,
                          AcademicProgramService programService) {
        this.studentRepo = studentRepo;
        this.userService = userService;
        this.programService = programService;
    }

    public boolean existsById(Long id) {
        return studentRepo
                .existsById(id);
    }

    public boolean existsByFacultyNumber(String facultyNumber) {
        return studentRepo
                .existsByFacultyNumber(facultyNumber);
    }

    public Student findById(Long id) throws ResourceNotFoundException {
        return studentRepo
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                String.format(STUDENT_NOT_FOUND_MSG, "with id=" + id)
                        ));
    }

    public Student findByUser(User user) throws ResourceNotFoundException {
        return studentRepo
                .findByUser(user)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                String.format(STUDENT_NOT_FOUND_MSG, "with userId=" + user.getId())
                        ));
    }

    public List<Student> findByFirstAndLastName(String firstName, String lastName) {
        return new ArrayList<>(studentRepo
                .findByFirstNameContainingOrLastNameContaining(firstName, lastName));
    }

    public List<Student> findByFullName(String firstName, String middleName, String lastName) {
        return new ArrayList<>(studentRepo
                .findByFirstNameContainingOrMiddleNameContainingOrLastNameContaining(firstName, middleName, lastName));
    }

    public Student findByFacultyNumber(String facultyNumber) throws ResourceNotFoundException{
        return studentRepo
                .findByFacultyNumber(facultyNumber)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                String.format(STUDENT_NOT_FOUND_MSG, "with faculty number '" + facultyNumber + "'")
                        ));
    }

    public List<Student> findByAcademicProgram(AcademicProgram program) {
        return new ArrayList<>(studentRepo
                .findByAcademicProgram(program));
    }

    public List<Student> findByAcademicYear(Integer year) {
        return new ArrayList<>(studentRepo
                .findByAcademicYear(year));
    }

    public List<Student> findByAcademicProgramAndAcademicYear(AcademicProgram program, Integer year) {
        return new ArrayList<>(studentRepo
                .findByAcademicProgramAndAcademicYear(program, year));
    }

    public List<Student> findByAcademicProgramAndAcademicYearAndStudentGroup(AcademicProgram program, Integer year, Integer group) {
        return new ArrayList<>(studentRepo
                .findByAcademicProgramAndAcademicYearAndStudentGroup(program, year, group));
    }

    public List<Student> findByActiveStatus(Boolean activeStatus) {
        return new ArrayList<>(studentRepo
                .findByActiveStatus(activeStatus));
    }

    public List<Student> findAll() {
        return studentRepo
                .findAll();
    }

    public List<Student> findAll(Sort sort) {
        return studentRepo
                .findAll(sort);
    }

    public List<Student> findAll(int pageNumber, int rowsPerPage) {
        return studentRepo
                .findAll(PageRequest.of(pageNumber - 1, rowsPerPage))
                .toList();
    }

    public List<Student> findProgramStudents(AcademicProgram program, Integer year, Integer group) {
        if (year == null)
            return findByAcademicProgram(program);
        else if (group == null)
            return findByAcademicProgramAndAcademicYear(program, year);
        else
            return findByAcademicProgramAndAcademicYearAndStudentGroup(program, year, group);
    }

    public Student addStudent(Student student) throws ResourceAlreadyExistsException, UserAlreadyExistsException, ResourceNotFoundException, BadResourceException {
        if(student.getId() != null && existsById(student.getId()))
            throw new ResourceAlreadyExistsException(
                    String.format(STUDENT_EXISTS_MSG, "with id=" + student.getId())
            );

        if(existsByFacultyNumber(student.getFacultyNumber()))
            throw new ResourceAlreadyExistsException(
                    String.format(STUDENT_EXISTS_MSG, "with faculty number '" + student.getFacultyNumber() + "'")
            );

        if(!programService.existsById(student.getAcademicProgram().getId()))
            throw new ResourceNotFoundException(STUDENT_PROGRAM_NOT_FOUND_MSG);

        student.setAcademicProgram(programService.findById(student.getAcademicProgram().getId()));
        if(student.getAcademicYear() > student.getAcademicProgram().getEducationPeriod())
            throw new BadResourceException(STUDENT_INVALID_YEAR_MSG);

        userService.registerUser(student.getUser());

        return studentRepo.save(student);
    }

    public Student updateStudent(Long id, Student student) throws ResourceNotFoundException, BadResourceException {
        Student oldStudent = findById(id);

        if(!oldStudent.getFacultyNumber().equals(student.getFacultyNumber()))
            throw new BadResourceException(STUDENT_FAC_NUMBER_OVERWRITE_MSG);

        if(!programService.existsById(student.getAcademicProgram().getId()))
            throw new ResourceNotFoundException(STUDENT_PROGRAM_NOT_FOUND_MSG);

        student.setAcademicProgram(programService.findById(student.getAcademicProgram().getId()));
        if(student.getAcademicYear() > student.getAcademicProgram().getEducationPeriod())
            throw new BadResourceException(STUDENT_INVALID_YEAR_MSG);

        student.setId(id);
        return studentRepo.save(student);
    }

    public void deleteStudent(Long id) throws ResourceNotFoundException {
        if(!existsById(id))
            throw new ResourceNotFoundException(
                    String.format(STUDENT_NOT_FOUND_MSG, "with id=" + id)
            );

        studentRepo.deleteById(id);

        // TODO - delete user as well
    }
}
