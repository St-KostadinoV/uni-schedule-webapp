package com.example.unischedulewebapp.service;

import com.example.unischedulewebapp.exception.ResourceAlreadyExistsException;
import com.example.unischedulewebapp.exception.ResourceNotFoundException;
import com.example.unischedulewebapp.model.AcademicDepartment;
import com.example.unischedulewebapp.model.AcademicProgram;
import com.example.unischedulewebapp.repository.AcademicProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AcademicProgramService {

    private final static String PROGRAM_NOT_FOUND_MSG =
            "Program %s not found!";
    private final static String PROGRAM_EXISTS_MSG =
            "Program %s already exists!";
    private final static String PROGRAM_DEPT_NOT_FOUND =
            "Program is part of a non-existent department!";

    private final AcademicProgramRepository programRepository;
    private final AcademicDepartmentService departmentService;

    @Autowired
    public AcademicProgramService(AcademicProgramRepository programRepository,
                                  AcademicDepartmentService departmentService) {
        this.programRepository = programRepository;
        this.departmentService = departmentService;
    }

    public boolean existsById(Long id){
        return programRepository
                .existsById(id);
    }

    public AcademicProgram findById(Long id) throws ResourceNotFoundException {
        return programRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                String.format(PROGRAM_NOT_FOUND_MSG, "with id=" + id)
                        ));
    }

    public List<AcademicProgram> findByDepartment(AcademicDepartment department) {
        return new ArrayList<>(programRepository
                .findByDepartment(department));
    }

    public List<AcademicProgram> findAll() {
        return programRepository
                .findAll();
    }

    public List<AcademicProgram> findAll(Sort sort) {
        return programRepository
                .findAll(sort);
    }

    public List<AcademicProgram> findAll(int pageNumber, int rowsPerPage) {
        return programRepository
                .findAll(PageRequest.of(pageNumber - 1, rowsPerPage))
                .toList();
    }

    public AcademicProgram addProgram(AcademicProgram program) throws ResourceAlreadyExistsException, ResourceNotFoundException {
        if(program.getId() != null && existsById(program.getId()))
            throw new ResourceAlreadyExistsException(
                    String.format(PROGRAM_EXISTS_MSG, "with id=" + program.getId())
            );

        if(!departmentService.existsById(program.getDepartment().getId()))
            throw new ResourceNotFoundException(PROGRAM_DEPT_NOT_FOUND);

        return programRepository.save(program);
    }

    public AcademicProgram updateProgram(Long id, AcademicProgram program) throws ResourceNotFoundException {
        if(!existsById(id))
            throw new ResourceNotFoundException(
                    String.format(PROGRAM_NOT_FOUND_MSG, "with id=" + id)
            );

        if(!departmentService.existsById(program.getDepartment().getId()))
            throw new ResourceNotFoundException(PROGRAM_DEPT_NOT_FOUND);

        program.setId(id);
        return programRepository.save(program);
    }

    public void deleteProgram(Long id) throws ResourceNotFoundException {
        if(!existsById(id))
            throw new ResourceNotFoundException(
                    String.format(PROGRAM_NOT_FOUND_MSG, "with id=" + id)
            );

        programRepository.deleteById(id);
    }
}
