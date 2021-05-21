package com.example.unischedulewebapp.service;

import com.example.unischedulewebapp.exception.ResourceAlreadyExistsException;
import com.example.unischedulewebapp.exception.ResourceNotFoundException;
import com.example.unischedulewebapp.model.AcademicDepartment;
import com.example.unischedulewebapp.model.AcademicFaculty;
import com.example.unischedulewebapp.repository.AcademicDepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AcademicDepartmentService {

    private final static String DEPT_NOT_FOUND_MSG =
            "Department %s not found!";
    private final static String DEPT_EXISTS_MSG =
            "Department %s already exists!";
    private final static String DEPT_FACULTY_NOT_FOUND_MSG =
            "Department is part of a non-existent faculty!";

    private final AcademicDepartmentRepository departmentRepository;
    private final AcademicFacultyService facultyService;

    @Autowired
    public AcademicDepartmentService(AcademicDepartmentRepository departmentRepository,
                                     AcademicFacultyService facultyService) {
        this.departmentRepository = departmentRepository;
        this.facultyService = facultyService;
    }

    public boolean existsById(Long id) {
        return departmentRepository
                .existsById(id);
    }

    public AcademicDepartment findById(Long id) throws ResourceNotFoundException {
        return departmentRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                String.format(DEPT_NOT_FOUND_MSG, "with id=" + id)
                        ));
    }

    public List<AcademicDepartment> findByFaculty(AcademicFaculty faculty) {
        return new ArrayList<>(departmentRepository
                .findByFaculty(faculty));
    }

    public List<AcademicDepartment> findAll() {
        return departmentRepository
                .findAll();
    }

    public List<AcademicDepartment> findAll(Sort sort) {
        return departmentRepository
                .findAll(sort);
    }

    public List<AcademicDepartment> findAll(int pageNumber, int rowsPerPage) {
        return departmentRepository
                .findAll(PageRequest.of(pageNumber - 1, rowsPerPage))
                .toList();
    }

    public AcademicDepartment addDepartment(AcademicDepartment department) throws ResourceAlreadyExistsException, ResourceNotFoundException {
        if(department.getId() != null && existsById(department.getId()))
            throw new ResourceAlreadyExistsException(
                    String.format(DEPT_EXISTS_MSG, "with id=" + department.getId())
            );

        if(!facultyService.existsById(department.getFaculty().getId()))
            throw new ResourceNotFoundException(DEPT_FACULTY_NOT_FOUND_MSG);

        return departmentRepository.save(department);
    }

    public AcademicDepartment updateDepartment(Long id, AcademicDepartment department) throws ResourceNotFoundException {
        if(!existsById(id))
            throw new ResourceNotFoundException(
                    String.format(DEPT_NOT_FOUND_MSG, "with id=" + id)
            );

        if(!facultyService.existsById(department.getFaculty().getId()))
            throw new ResourceNotFoundException(DEPT_FACULTY_NOT_FOUND_MSG);

        department.setId(id);
        return departmentRepository.save(department);
    }

    public void deleteDepartment(Long id) throws ResourceNotFoundException {
        if(!existsById(id))
            throw new ResourceNotFoundException(
                    String.format(DEPT_NOT_FOUND_MSG, "with id=" + id)
            );

        departmentRepository.deleteById(id);
    }
}
