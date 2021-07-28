package com.example.unischedulewebapp.service;

import com.example.unischedulewebapp.auth.AppUser;
import com.example.unischedulewebapp.auth.AppUserService;
import com.example.unischedulewebapp.auth.exception.UserAlreadyExistsException;
import com.example.unischedulewebapp.exception.ResourceAlreadyExistsException;
import com.example.unischedulewebapp.exception.ResourceNotFoundException;
import com.example.unischedulewebapp.model.AcademicDepartment;
import com.example.unischedulewebapp.model.enums.AcademicTitle;
import com.example.unischedulewebapp.model.Instructor;
import com.example.unischedulewebapp.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InstructorService {

    private final static String INSTRUCTOR_NOT_FOUND_MSG =
            "Instructor %s not found!";
    private final static String INSTRUCTOR_EXISTS_MSG =
            "Instructor %s already exists!";
    private final static String INSTRUCTOR_DEPT_NOT_FOUND_MSG =
            "Instructor is part of a non-existent department!";

    private final InstructorRepository instructorRepository;
    private final AppUserService userService;
    private final AcademicDepartmentService departmentService;

    @Autowired
    public InstructorService(InstructorRepository instructorRepository,
                             AppUserService userService,
                             AcademicDepartmentService departmentService) {
        this.instructorRepository = instructorRepository;
        this.userService = userService;
        this.departmentService = departmentService;
    }

    public boolean existsById(Long id) {
        return instructorRepository
                .existsById(id);
    }

    public Instructor findById(Long id) throws ResourceNotFoundException {
        return instructorRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                               String.format(INSTRUCTOR_NOT_FOUND_MSG, "with id=" + id)
                        ));
    }

    public Instructor findByUserDetails(AppUser userDetails) throws ResourceNotFoundException {
        return instructorRepository
                .findByUserDetails(userDetails)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                String.format(INSTRUCTOR_NOT_FOUND_MSG, "with userId=" + userDetails.getId())
                        ));
    }

    public List<Instructor> findByTitle(AcademicTitle title) {
        return new ArrayList<>(instructorRepository
                .findByTitle(title));
    }

    public List<Instructor> findByDepartment(AcademicDepartment department) {
        return new ArrayList<>(instructorRepository
                .findByDepartment(department));
    }

    public List<Instructor> findAll() {
        return instructorRepository
                .findAll();
    }

    public List<Instructor> findAll(Sort sort) {
        return instructorRepository
                .findAll(sort);
    }

    public List<Instructor> findAll(int pageNumber, int rowsPerPage) {
        return instructorRepository
                .findAll(PageRequest.of(pageNumber - 1, rowsPerPage))
                .toList();
    }

    public Instructor addInstructor(Instructor instructor) throws ResourceAlreadyExistsException, UserAlreadyExistsException, ResourceNotFoundException {
        if (instructor.getId() != null && existsById(instructor.getId()))
            throw new ResourceAlreadyExistsException(
                    String.format(INSTRUCTOR_EXISTS_MSG, "with id=" + instructor.getId())
            );

        if(!departmentService.existsById(instructor.getDepartment().getId()))
            throw new ResourceNotFoundException(INSTRUCTOR_DEPT_NOT_FOUND_MSG);

        userService.registerUser(instructor.getUserDetails());

        return instructorRepository.save(instructor);
    }

    public Instructor updateInstructor(Long id, Instructor instructor) throws ResourceNotFoundException {
        if(!existsById(id))
            throw new ResourceNotFoundException(
                    String.format(INSTRUCTOR_NOT_FOUND_MSG, "with id=" + id)
            );

        if(!departmentService.existsById(instructor.getDepartment().getId()))
            throw new ResourceNotFoundException(INSTRUCTOR_DEPT_NOT_FOUND_MSG);

        instructor.setId(id);
        return instructorRepository.save(instructor);
    }

    public Instructor updateInstructorEmail(Instructor instructor, String email) throws ResourceNotFoundException {
        if(instructor.getId() == null && !existsById(instructor.getId()))
            throw new ResourceNotFoundException(
                    String.format(INSTRUCTOR_NOT_FOUND_MSG, "with id=" + instructor.getId())
            );

        if(!departmentService.existsById(instructor.getDepartment().getId()))
            throw new ResourceNotFoundException(INSTRUCTOR_DEPT_NOT_FOUND_MSG);

        instructor.setEmail(email);
        return instructorRepository.save(instructor);
    }

    public void deleteInstructor(Long id) throws ResourceNotFoundException {
        if(!existsById(id))
            throw new ResourceNotFoundException(
                    String.format(INSTRUCTOR_NOT_FOUND_MSG, "with id=" + id)
            );

        instructorRepository.deleteById(id);
    }
}
