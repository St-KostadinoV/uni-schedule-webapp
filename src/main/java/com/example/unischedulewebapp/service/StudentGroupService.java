package com.example.unischedulewebapp.service;

import com.example.unischedulewebapp.exception.ResourceAlreadyExistsException;
import com.example.unischedulewebapp.exception.ResourceNotFoundException;
import com.example.unischedulewebapp.model.AcademicProgram;
import com.example.unischedulewebapp.model.StudentGroup;
import com.example.unischedulewebapp.repository.StudentGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentGroupService {

    private final static String STUDENTGROUP_NOT_FOUND_MSG =
            "Student group %s not found!";
    private final static String STUDENTGROUP_EXISTS_MSG =
            "Student group %s already exists!";
    private final static String STUDENTGROUP_PROGRAM_NOT_FOUND_MSG =
            "Student group is part of a non-existent program!";

    private final StudentGroupRepository studentGroupRepository;
    private final AcademicProgramService programService;

    @Autowired
    public StudentGroupService(StudentGroupRepository studentGroupRepository, AcademicProgramService programService) {
        this.studentGroupRepository = studentGroupRepository;
        this.programService = programService;
    }

    public boolean existsById(Long id) {
        return studentGroupRepository
                .existsById(id);
    }

    public StudentGroup findById(Long id) throws ResourceNotFoundException {
        return studentGroupRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                String.format(STUDENTGROUP_NOT_FOUND_MSG, "with id=" + id)
                        ));
    }

    public List<StudentGroup> findByAcademicProgram(AcademicProgram program) {
        return new ArrayList<>(studentGroupRepository
                .findByAcademicProgram(program));
    }

    public List<StudentGroup> findByAcademicYear(Integer year) {
        return new ArrayList<>(studentGroupRepository
                .findByAcademicYear(year));
    }

    public List<StudentGroup> findByAcademicProgramAndAcademicYear(AcademicProgram program, Integer year) {
        return new ArrayList<>(studentGroupRepository
                .findByAcademicProgramAndAcademicYear(program, year));
    }

    public List<StudentGroup> findByAcademicProgramAndAcademicYearAndGroupNumber(AcademicProgram program, Integer year, Integer groupNumber) {
        return new ArrayList<>(studentGroupRepository
                .findByAcademicProgramAndAcademicYearAndGroupNumber(program, year, groupNumber));
    }

    public List<StudentGroup> findAll() {
        return studentGroupRepository
                .findAll();
    }

    public List<StudentGroup> findAll(Sort sort) {
        return studentGroupRepository
                .findAll(sort);
    }

    public List<StudentGroup> findAll(int pageNumber, int rowsPerPage) {
        return studentGroupRepository
                .findAll(PageRequest.of(pageNumber - 1, rowsPerPage))
                .toList();
    }

    public StudentGroup addStudentGroup(StudentGroup studentGroup) throws ResourceAlreadyExistsException, ResourceNotFoundException {
        if(studentGroup.getId() != null && existsById(studentGroup.getId()))
            throw new ResourceAlreadyExistsException(
                    String.format(STUDENTGROUP_EXISTS_MSG, "with id=" + studentGroup.getId())
            );

        if(!programService.existsById(studentGroup.getAcademicProgram().getId()))
            throw new ResourceNotFoundException(STUDENTGROUP_PROGRAM_NOT_FOUND_MSG);

        return studentGroupRepository.save(studentGroup);
    }

    public StudentGroup updateStudentGroup(Long id, StudentGroup studentGroup) throws ResourceNotFoundException {
        if(!existsById(id))
            throw new ResourceNotFoundException(
                    String.format(STUDENTGROUP_NOT_FOUND_MSG, "with id=" + id)
            );

        if(!programService.existsById(studentGroup.getAcademicProgram().getId()))
            throw new ResourceNotFoundException(STUDENTGROUP_PROGRAM_NOT_FOUND_MSG);

        studentGroup.setId(id);
        return studentGroupRepository.save(studentGroup);
    }

    public void deleteStudentGroup(Long id) throws ResourceNotFoundException {
        if(!existsById(id))
            throw new ResourceNotFoundException(
                    String.format(STUDENTGROUP_NOT_FOUND_MSG, "with id=" + id)
            );

        studentGroupRepository.deleteById(id);
    }
}
