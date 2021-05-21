package com.example.unischedulewebapp.service;

import com.example.unischedulewebapp.auth.AppUser;
import com.example.unischedulewebapp.auth.AppUserService;
import com.example.unischedulewebapp.auth.exception.UserAlreadyExistsException;
import com.example.unischedulewebapp.exception.ResourceAlreadyExistsException;
import com.example.unischedulewebapp.exception.ResourceNotFoundException;
import com.example.unischedulewebapp.model.AcademicProgram;
import com.example.unischedulewebapp.model.Student;
import com.example.unischedulewebapp.repository.StudentRepository;
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

    private final StudentRepository studentRepository;
    private final AppUserService userService;
    private final AcademicProgramService programService;

    @Autowired
    public StudentService(StudentRepository studentRepository,
                          AppUserService userService,
                          AcademicProgramService programService) {
        this.studentRepository = studentRepository;
        this.userService = userService;
        this.programService = programService;
    }

    public boolean existsById(Long id) {
        return studentRepository
                .existsById(id);
    }

    public boolean existsByFacultyNumber(String facultyNumber) {
        return studentRepository
                .existsByFacultyNumber(facultyNumber);
    }

    public Student findById(Long id) throws ResourceNotFoundException {
        return studentRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                String.format(STUDENT_NOT_FOUND_MSG, "with id=" + id)
                        ));
    }

    public Student findByUserDetails(AppUser userDetails) throws ResourceNotFoundException {
        return studentRepository
                .findByUserDetails(userDetails)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                String.format(STUDENT_NOT_FOUND_MSG, "with userId=" + userDetails.getId())
                        ));
    }

    public Student findByFacultyNumber(String facultyNumber) throws ResourceNotFoundException{
        return studentRepository
                .findByFacultyNumber(facultyNumber)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                String.format(STUDENT_NOT_FOUND_MSG, "with faculty number '" + facultyNumber + "'")
                        ));
    }

    public List<Student> findByAdmissionStream(Integer stream) {
        return new ArrayList<>(studentRepository
                .findByAdmissionStream(stream));
    }

    public List<Student> findByAcademicProgram(AcademicProgram program) {
        return new ArrayList<>(studentRepository
                .findByAcademicProgram(program));
    }

    public List<Student> findByAcademicYear(Integer year) {
        return new ArrayList<>(studentRepository
                .findByAcademicYear(year));
    }

    public List<Student> findByAcademicProgramAndStudentGroup(AcademicProgram program, Integer group) {
        return new ArrayList<>(studentRepository
                .findByAcademicProgramAndStudentGroup(program, group));
    }

    public List<Student> findByAcademicProgramAndStudentGroupAndAcademicYear(AcademicProgram program, Integer group, Integer year) {
        return new ArrayList<>(studentRepository
                .findByAcademicProgramAndStudentGroupAndAcademicYear(program, group, year));
    }

    public List<Student> findByActiveStatus(Boolean activeStatus) {
        return new ArrayList<>(studentRepository
                .findByActiveStatus(activeStatus));
    }

    public List<Student> findAll() {
        return studentRepository
                .findAll();
    }

    public List<Student> findAll(Sort sort) {
        return studentRepository
                .findAll(sort);
    }

    public List<Student> findAll(int pageNumber, int rowsPerPage) {
        return studentRepository
                .findAll(PageRequest.of(pageNumber - 1, rowsPerPage))
                .toList();
    }

    public Student addStudent(Student student) throws ResourceAlreadyExistsException, UserAlreadyExistsException, ResourceNotFoundException {
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

        userService.registerUser(student.getUserDetails());

        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student student) throws ResourceNotFoundException {
        if(!existsById(id))
            throw new ResourceNotFoundException(
                    String.format(STUDENT_NOT_FOUND_MSG, "with id=" + id)
            );

        // TODO - check if updated data overwrites faculty number

        if(!programService.existsById(student.getAcademicProgram().getId()))
            throw new ResourceNotFoundException(STUDENT_PROGRAM_NOT_FOUND_MSG);

        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) throws ResourceNotFoundException {
        if(!existsById(id))
            throw new ResourceNotFoundException(
                    String.format(STUDENT_NOT_FOUND_MSG, "with id=" + id)
            );

        studentRepository.deleteById(id);
    }
}
