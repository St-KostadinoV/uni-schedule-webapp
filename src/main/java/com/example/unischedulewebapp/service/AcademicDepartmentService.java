package com.example.unischedulewebapp.service;

import com.example.unischedulewebapp.exception.ResourceNotFoundException;
import com.example.unischedulewebapp.model.AcademicDepartment;
import com.example.unischedulewebapp.repository.AcademicDepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcademicDepartmentService {

    private final static String DEPT_NOT_FOUND_MSG =
            "Department %s not found!";

    private final AcademicDepartmentRepository departmentRepository;

    @Autowired
    public AcademicDepartmentService(AcademicDepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
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


}
