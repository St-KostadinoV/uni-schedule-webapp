package com.example.unischedulewebapp.service;

import com.example.unischedulewebapp.exception.ResourceAlreadyExistsException;
import com.example.unischedulewebapp.exception.ResourceNotFoundException;
import com.example.unischedulewebapp.model.AcademicDepartment;
import com.example.unischedulewebapp.model.AcademicTitle;
import com.example.unischedulewebapp.model.Teacher;
import com.example.unischedulewebapp.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    private final static String TEACHER_NOT_FOUND_MSG =
            "Teacher %s not found!";
    private final static String TEACHER_EXISTS_MSG =
            "Teacher %s already exists!";

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public boolean existsById(Long id) {
        return teacherRepository
                .existsById(id);
    }

    public Teacher findById(Long id) throws ResourceNotFoundException {
        return teacherRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                               String.format(TEACHER_NOT_FOUND_MSG, "with id=" + id)
                        ));
    }

    public List<Teacher> findAll(int pageNumber, int rowsPerPage) {
        return teacherRepository
                .findAll(PageRequest.of(pageNumber - 1, rowsPerPage))
                .toList();
    }

    public void addTeacher(Teacher teacher) throws ResourceAlreadyExistsException {
        if (teacher.getId() != null && !teacherRepository.existsById(teacher.getId()))
            throw new ResourceAlreadyExistsException(
                    String.format(TEACHER_EXISTS_MSG, "with id=" + teacher.getId())
            );

        // TODO - check if teacher data is valid

        teacherRepository.save(teacher);
    }

    // TODO - check if updated data is still valid

    public void updateTeacher(Long id, Teacher teacher) throws ResourceNotFoundException {
        if(!teacherRepository.existsById(id))
            throw new ResourceNotFoundException(
                    String.format(TEACHER_NOT_FOUND_MSG, "with id=" + id)
            );

        teacher.setId(id);
        teacherRepository.save(teacher);
    }

    public void updateTeacherNames(Long id, String firstName, String middleName, String lastName) throws ResourceNotFoundException {
        Teacher teacher = teacherRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                String.format(TEACHER_NOT_FOUND_MSG, "with id=" + id)
                        ));

        teacher.setFirstName(firstName);
        teacher.setMiddleName(middleName);
        teacher.setLastName(lastName);
        teacherRepository.save(teacher);
    }

    public void updateTeacherEmail(Long id, String email) throws ResourceNotFoundException {
        Teacher teacher = teacherRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                String.format(TEACHER_NOT_FOUND_MSG, "with id=" + id)
                        ));

        teacher.setEmail(email);
        teacherRepository.save(teacher);
    }

    public void updateTeacherDepartment(Long id, AcademicDepartment department) throws ResourceNotFoundException {
        Teacher teacher = teacherRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                String.format(TEACHER_NOT_FOUND_MSG, "with id=" + id)
                        ));

        // TODO - check if department exists

        teacher.setDepartment(department);
        teacherRepository.save(teacher);
    }

    public void updateTeacherTitle(Long id, AcademicTitle title) throws ResourceNotFoundException {
        Teacher teacher = teacherRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                String.format(TEACHER_NOT_FOUND_MSG, "with id=" + id)
                        ));

        teacher.setAcademicTitle(title);
        teacherRepository.save(teacher);
    }

    public void updateTeacherHonorary(Long id, Boolean isHonorary) throws ResourceNotFoundException {
        Teacher teacher = teacherRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                String.format(TEACHER_NOT_FOUND_MSG, "with id=" + id)
                        ));

        teacher.setHonorary(isHonorary);
        teacherRepository.save(teacher);
    }

    public void deleteTeacher(Long id) throws ResourceNotFoundException {
        if(!teacherRepository.existsById(id))
            throw new ResourceNotFoundException(
                    String.format(TEACHER_NOT_FOUND_MSG, "with id=" + id)
            );

        teacherRepository.deleteById(id);
    }
}
