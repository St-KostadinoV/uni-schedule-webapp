package com.example.unischedulewebapp.service;

import com.example.unischedulewebapp.auth.AppUser;
import com.example.unischedulewebapp.auth.AppUserService;
import com.example.unischedulewebapp.auth.exception.UserAlreadyExistsException;
import com.example.unischedulewebapp.exception.ResourceAlreadyExistsException;
import com.example.unischedulewebapp.exception.ResourceNotFoundException;
import com.example.unischedulewebapp.model.AcademicDepartment;
import com.example.unischedulewebapp.model.enums.AcademicTitle;
import com.example.unischedulewebapp.model.Teacher;
import com.example.unischedulewebapp.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherService {

    private final static String TEACHER_NOT_FOUND_MSG =
            "Teacher %s not found!";
    private final static String TEACHER_EXISTS_MSG =
            "Teacher %s already exists!";
    private final static String TEACHER_DEPT_NOT_FOUND_MSG =
            "Teacher is part of a non-existent department!";

    private final TeacherRepository teacherRepository;
    private final AppUserService userService;
    private final AcademicDepartmentService departmentService;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository,
                          AppUserService userService,
                          AcademicDepartmentService departmentService) {
        this.teacherRepository = teacherRepository;
        this.userService = userService;
        this.departmentService = departmentService;
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

    public Teacher findByUserDetails(AppUser userDetails) throws ResourceNotFoundException {
        return teacherRepository
                .findByUserDetails(userDetails)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                String.format(TEACHER_NOT_FOUND_MSG, "with userId=" + userDetails.getId())
                        ));
    }

    public List<Teacher> findByTitle(AcademicTitle title) {
        return new ArrayList<>(teacherRepository
                .findByTitle(title));
    }

    public List<Teacher> findByDepartment(AcademicDepartment department) {
        return new ArrayList<>(teacherRepository
                .findByDepartment(department));
    }

    public List<Teacher> findAll() {
        return teacherRepository
                .findAll();
    }

    public List<Teacher> findAll(Sort sort) {
        return teacherRepository
                .findAll(sort);
    }

    public List<Teacher> findAll(int pageNumber, int rowsPerPage) {
        return teacherRepository
                .findAll(PageRequest.of(pageNumber - 1, rowsPerPage))
                .toList();
    }

    public void addTeacher(Teacher teacher) throws ResourceAlreadyExistsException, UserAlreadyExistsException, ResourceNotFoundException {
        if (teacher.getId() != null && existsById(teacher.getId()))
            throw new ResourceAlreadyExistsException(
                    String.format(TEACHER_EXISTS_MSG, "with id=" + teacher.getId())
            );

        if(!departmentService.existsById(teacher.getDepartment().getId()))
            throw new ResourceNotFoundException(TEACHER_DEPT_NOT_FOUND_MSG);

        userService.registerUser(teacher.getUserDetails());

        teacherRepository.save(teacher);
    }

    public void updateTeacher(Long id, Teacher teacher) throws ResourceNotFoundException {
        if(!existsById(id))
            throw new ResourceNotFoundException(
                    String.format(TEACHER_NOT_FOUND_MSG, "with id=" + id)
            );

        // TODO - check user details

        if(!departmentService.existsById(teacher.getDepartment().getId()))
            throw new ResourceNotFoundException(TEACHER_DEPT_NOT_FOUND_MSG);

        teacher.setId(id);
        teacherRepository.save(teacher);
    }

    public void deleteTeacher(Long id) throws ResourceNotFoundException {
        if(!existsById(id))
            throw new ResourceNotFoundException(
                    String.format(TEACHER_NOT_FOUND_MSG, "with id=" + id)
            );

        teacherRepository.deleteById(id);
    }
}
