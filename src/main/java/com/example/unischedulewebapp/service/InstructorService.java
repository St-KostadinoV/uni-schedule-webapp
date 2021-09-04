package com.example.unischedulewebapp.service;

import com.example.unischedulewebapp.auth.exception.UserAlreadyExistsException;
import com.example.unischedulewebapp.exception.ResourceAlreadyExistsException;
import com.example.unischedulewebapp.exception.ResourceNotFoundException;
import com.example.unischedulewebapp.model.AcademicDepartment;
import com.example.unischedulewebapp.model.User;
import com.example.unischedulewebapp.model.enums.AcademicDegree;
import com.example.unischedulewebapp.model.enums.AcademicTitle;
import com.example.unischedulewebapp.model.Instructor;
import com.example.unischedulewebapp.model.enums.ProfessionalQualification;
import com.example.unischedulewebapp.repository.InstructorRepo;
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

    private final InstructorRepo instructorRepo;
    private final UserService userService;
    private final AcademicDepartmentService departmentService;

    @Autowired
    public InstructorService(InstructorRepo instructorRepo,
                             UserService userService,
                             AcademicDepartmentService departmentService) {
        this.instructorRepo = instructorRepo;
        this.userService = userService;
        this.departmentService = departmentService;
    }

    public boolean existsById(Long id) {
        return instructorRepo
                .existsById(id);
    }

    public Instructor findById(Long id) throws ResourceNotFoundException {
        return instructorRepo
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                               String.format(INSTRUCTOR_NOT_FOUND_MSG, "with id=" + id)
                        ));
    }

    public Instructor findByUser(User user) throws ResourceNotFoundException {
        return instructorRepo
                .findByUser(user)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                String.format(INSTRUCTOR_NOT_FOUND_MSG, "with userId=" + user.getId())
                        ));
    }

    public List<Instructor> findByFirstAndLastName(String firstName, String lastName) {
        return new ArrayList<>(instructorRepo
                .findByFirstNameContainingOrLastNameContaining(firstName, lastName));
    }

    public List<Instructor> findByFullName(String firstName, String middleName, String lastName) {
        return new ArrayList<>(instructorRepo
                .findByFirstNameContainingOrMiddleNameContainingOrLastNameContaining(firstName, middleName, lastName));
    }

    public List<Instructor> findByTitle(AcademicTitle title) {
        return new ArrayList<>(instructorRepo
                .findByTitle(title));
    }

    public List<Instructor> findByTitle(AcademicDegree degree) {
        return new ArrayList<>(instructorRepo
                .findByDegree(degree));
    }

    public List<Instructor> findByTitle(ProfessionalQualification qualification) {
        return new ArrayList<>(instructorRepo
                .findByQualification(qualification));
    }

    public List<Instructor> findByDepartment(AcademicDepartment department) {
        return new ArrayList<>(instructorRepo
                .findByDepartment(department));
    }

    public List<Instructor> findAll() {
        return instructorRepo
                .findAll();
    }

    public List<Instructor> findAll(Sort sort) {
        return instructorRepo
                .findAll(sort);
    }

    public List<Instructor> findAll(int pageNumber, int rowsPerPage) {
        return instructorRepo
                .findAll(PageRequest.of(pageNumber - 1, rowsPerPage))
                .toList();
    }

    public Instructor addInstructor(Instructor instructor) throws ResourceAlreadyExistsException, UserAlreadyExistsException, ResourceNotFoundException {
        if (instructor.getId() != null && existsById(instructor.getId()))
            throw new ResourceAlreadyExistsException(
                    String.format(INSTRUCTOR_EXISTS_MSG, "with id=" + instructor.getId())
            );

        if (!departmentService.existsById(instructor.getDepartment().getId()))
            throw new ResourceNotFoundException(INSTRUCTOR_DEPT_NOT_FOUND_MSG);

        userService.registerUser(instructor.getUser());

        return instructorRepo.save(instructor);
    }

    public Instructor updateInstructor(Long id, Instructor instructor) throws ResourceNotFoundException {
        if(!existsById(id))
            throw new ResourceNotFoundException(
                    String.format(INSTRUCTOR_NOT_FOUND_MSG, "with id=" + id)
            );

        if(!departmentService.existsById(instructor.getDepartment().getId()))
            throw new ResourceNotFoundException(INSTRUCTOR_DEPT_NOT_FOUND_MSG);

        instructor.setId(id);
        return instructorRepo.save(instructor);
    }

    public Instructor updateInstructorEmail(Instructor instructor, String email) throws ResourceNotFoundException {
        if(instructor.getId() == null && !existsById(instructor.getId()))
            throw new ResourceNotFoundException(
                    String.format(INSTRUCTOR_NOT_FOUND_MSG, "with id=" + instructor.getId())
            );

        if(!departmentService.existsById(instructor.getDepartment().getId()))
            throw new ResourceNotFoundException(INSTRUCTOR_DEPT_NOT_FOUND_MSG);

        instructor.setEmail(email);
        return instructorRepo.save(instructor);
    }

    public void deleteInstructor(Long id) throws ResourceNotFoundException {
        if(!existsById(id))
            throw new ResourceNotFoundException(
                    String.format(INSTRUCTOR_NOT_FOUND_MSG, "with id=" + id)
            );

        instructorRepo.deleteById(id);

        // TODO - delete user as well
    }
}
