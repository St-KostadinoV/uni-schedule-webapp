package com.example.unischedulewebapp.service;

import com.example.unischedulewebapp.exception.ResourceAlreadyExistsException;
import com.example.unischedulewebapp.exception.ResourceNotFoundException;
import com.example.unischedulewebapp.model.AcademicDiscipline;
import com.example.unischedulewebapp.model.AcademicProgram;
import com.example.unischedulewebapp.model.ProgramDiscipline;
import com.example.unischedulewebapp.repository.ProgramDisciplineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProgramDisciplineService {

    private final static String PROGRAM_DSCPL_NOT_FOUND_MSG =
            "Discipline %s is not part of program !";         // TODO - rework message
    private final static String PROGRAM_DSCPL_EXISTS_MSG =
            "Discipline %s is already part of program !";     // TODO - rework message
    private final static String PROGRAM_NOT_FOUND_MSG =
            "Non-existent program!";
    private final static String DSCPL_NOT_FOUND_MSG =
            "Non-existent discipline!";

    private final ProgramDisciplineRepository programDisciplineRepository;
    private final AcademicProgramService programService;
    private final AcademicDisciplineService disciplineService;

    @Autowired
    public ProgramDisciplineService(ProgramDisciplineRepository programDisciplineRepository,
                                    AcademicProgramService programService,
                                    AcademicDisciplineService disciplineService) {
        this.programDisciplineRepository = programDisciplineRepository;
        this.programService = programService;
        this.disciplineService = disciplineService;
    }

    public boolean existsById(Long id){
        return programDisciplineRepository
                .existsById(id);
    }

    public ProgramDiscipline findById(Long id) throws ResourceNotFoundException {
        return programDisciplineRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                String.format(PROGRAM_DSCPL_NOT_FOUND_MSG, id)
                        ));
    }

    public List<ProgramDiscipline> findByProgram(AcademicProgram program) {
        return new ArrayList<>(programDisciplineRepository
                .findByProgram(program));
    }

    public List<ProgramDiscipline> findByDiscipline(AcademicDiscipline discipline) {
        return new ArrayList<>(programDisciplineRepository
                .findByDiscipline(discipline));
    }

    public List<ProgramDiscipline> findByProgramAndAcademicYear(AcademicProgram program, Integer year) {
        return new ArrayList<>(programDisciplineRepository
                .findByProgramAndAcademicYear(program, year));
    }

    public List<ProgramDiscipline> findAll() {
        return programDisciplineRepository
                .findAll();
    }

    public List<ProgramDiscipline> findAll(int pageNumber, int rowsPerPage) {
        return programDisciplineRepository
                .findAll(PageRequest.of(pageNumber - 1, rowsPerPage))
                .toList();
    }

    public void addProgramDiscipline(ProgramDiscipline programDiscipline) throws ResourceAlreadyExistsException, ResourceNotFoundException {
        if(programDiscipline.getId()!=null && existsById(programDiscipline.getId()))
            throw new ResourceAlreadyExistsException(
                    String.format(PROGRAM_DSCPL_EXISTS_MSG, programDiscipline.getId())
            );

        if(!programService.existsById(programDiscipline.getProgram().getId()))
            throw new ResourceNotFoundException(
                    PROGRAM_NOT_FOUND_MSG
            );

        if(!disciplineService.existsById(programDiscipline.getDiscipline().getId()))
            throw new ResourceNotFoundException(
                    DSCPL_NOT_FOUND_MSG
            );

        programDisciplineRepository.save(programDiscipline);
    }

    public void updateProgramDiscipline(Long id, ProgramDiscipline programDiscipline) throws ResourceNotFoundException {
        if(!existsById(id))
            throw new ResourceNotFoundException(
                    String.format(PROGRAM_DSCPL_NOT_FOUND_MSG, id)
            );

        if(!programService.existsById(programDiscipline.getProgram().getId()))
            throw new ResourceNotFoundException(
                    PROGRAM_NOT_FOUND_MSG
            );

        if(!disciplineService.existsById(programDiscipline.getDiscipline().getId()))
            throw new ResourceNotFoundException(
                    DSCPL_NOT_FOUND_MSG
            );

        programDiscipline.setId(id);
        programDisciplineRepository.save(programDiscipline);
    }

    public void deleteProgramDiscipline(Long id) throws ResourceNotFoundException {
        if(!existsById(id))
            throw new ResourceNotFoundException(
                    String.format(PROGRAM_DSCPL_NOT_FOUND_MSG, id)
            );

        programDisciplineRepository.deleteById(id);
    }
}
