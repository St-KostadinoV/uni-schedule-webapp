package com.example.unischedulewebapp.service;

import com.example.unischedulewebapp.exception.ResourceAlreadyExistsException;
import com.example.unischedulewebapp.exception.ResourceNotFoundException;
import com.example.unischedulewebapp.model.AcademicProgram;
import com.example.unischedulewebapp.model.Student;
import com.example.unischedulewebapp.model.Teacher;
import com.example.unischedulewebapp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final static String STUDENT_NOT_FOUND_MSG =
            "Student %s not found!";
    private final static String STUDENT_EXISTS_MSG =
            "Student %s already exists!";

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public boolean existsById(Long id) {
        return studentRepository
                .existsById(id);
    }

    public Student findById(Long id) throws ResourceNotFoundException {
        return studentRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                String.format(STUDENT_NOT_FOUND_MSG, "with id=" + id)
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

    public List<Student> findAll(int pageNumber, int rowsPerPage) {
        return studentRepository
                .findAll(PageRequest.of(pageNumber - 1, rowsPerPage))
                .toList();
    }

    public void addStudent(Student student) throws ResourceAlreadyExistsException {
        if(student.getId() != null && !studentRepository.existsById(student.getId()))
            throw new ResourceAlreadyExistsException(
                    String.format(STUDENT_EXISTS_MSG, "with id=" + student.getId())
            );

    }

    public void updateStudent(Long id, Student student) throws ResourceNotFoundException {
        if(!studentRepository.existsById(id))
            throw new ResourceNotFoundException(
                    String.format(STUDENT_NOT_FOUND_MSG, "with id=" + id)
            );

        // TODO - check if student data is valid

        studentRepository.save(student);
    }

    public void updateStudentStream(Long id, Integer admissionStream) throws ResourceNotFoundException {
        Student student = studentRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                String.format(STUDENT_NOT_FOUND_MSG, "with id=" + id)
                        ));

        student.setAdmissionStream(admissionStream);
        studentRepository.save(student);
    }

    public void updateStudentProgram(Long id, AcademicProgram program) throws ResourceNotFoundException {
        Student student = studentRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                String.format(STUDENT_NOT_FOUND_MSG, "with id=" + id)
                        ));

        // TODO - check if program exists

        student.setAcademicProgram(program);
        studentRepository.save(student);
    }

    public void updateStudentYear(Long id, Integer year) throws ResourceNotFoundException {
        Student student = studentRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                String.format(STUDENT_NOT_FOUND_MSG, "with id=" + id)
                        ));

        student.setAcademicYear(year);
        studentRepository.save(student);
    }

    public void updateStudentGroup(Long id, Integer group) throws ResourceNotFoundException {
        Student student = studentRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                String.format(STUDENT_NOT_FOUND_MSG, "with id=" + id)
                        ));

        student.setStudentGroup(group);
        studentRepository.save(student);
    }

    public void updateStudentStatus(Long id, Boolean isActive) throws ResourceNotFoundException {
        Student student = studentRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                String.format(STUDENT_NOT_FOUND_MSG, "with id=" + id)
                        ));

        student.setActive(isActive);
        studentRepository.save(student);
    }

    public void deleteStudent(Long id) throws ResourceNotFoundException {
        if(!studentRepository.existsById(id))
            throw new ResourceNotFoundException(
                    String.format(STUDENT_NOT_FOUND_MSG, "with id=" + id)
            );

        studentRepository.deleteById(id);
    }
}
