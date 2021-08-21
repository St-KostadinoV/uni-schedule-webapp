package com.example.unischedulewebapp.service;

import com.example.unischedulewebapp.exception.BadResourceException;
import com.example.unischedulewebapp.exception.ResourceAlreadyExistsException;
import com.example.unischedulewebapp.exception.ResourceNotFoundException;
import com.example.unischedulewebapp.model.AcademicDiscipline;
import com.example.unischedulewebapp.model.AcademicProgram;
import com.example.unischedulewebapp.model.ProgramDiscipline;
import com.example.unischedulewebapp.repository.ProgramDisciplineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProgramDisciplineService {

    private final static String PROGRAM_DSCPL_NOT_FOUND_MSG =
            "Program-discipline combination %s not found!";
    private final static String PROGRAM_DSCPL_EXISTS_MSG =
            "Program-discipline combination %s already exists!";
    private final static String PROGRAM_NOT_FOUND_MSG =
            "Non-existent program!";
    private final static String DSCPL_NOT_FOUND_MSG =
            "Non-existent discipline!";
    private final static String PROGRAM_DSCPL_INVALID_YEAR_MSG =
            "Discipline's year is outside of program's education period!";

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

    public List<ProgramDiscipline> findByProgramAndDiscipline(AcademicProgram program, AcademicDiscipline discipline) {
        return new ArrayList<>(programDisciplineRepository
                .findByProgramAndDiscipline(program, discipline));
    }

    public List<ProgramDiscipline> findByProgramAndAcademicYear(AcademicProgram program, Integer year) {
        return new ArrayList<>(programDisciplineRepository
                .findByProgramAndAcademicYear(program, year));
    }

    public List<ProgramDiscipline> findAll() {
        return programDisciplineRepository
                .findAll();
    }

    public List<ProgramDiscipline> findAll(Sort sort) {
        return programDisciplineRepository
                .findAll(sort);
    }

    public List<ProgramDiscipline> findAll(int pageNumber, int rowsPerPage) {
        return programDisciplineRepository
                .findAll(PageRequest.of(pageNumber - 1, rowsPerPage))
                .toList();
    }

    public List<AcademicDiscipline> findProgramDisciplines(AcademicProgram program, Integer year) {
        if (year == null)
            return findByProgram(program)
                    .stream()
                    .map(ProgramDiscipline::getDiscipline)
                    .collect(Collectors.toList());
        else
            return findByProgramAndAcademicYear(program, year)
                    .stream()
                    .map(ProgramDiscipline::getDiscipline)
                    .collect(Collectors.toList());
    }

    public ProgramDiscipline addProgramDiscipline(ProgramDiscipline programDiscipline) throws ResourceAlreadyExistsException, ResourceNotFoundException, BadResourceException {
        if(programDiscipline.getId() != null && existsById(programDiscipline.getId()))
            throw new ResourceAlreadyExistsException(
                    String.format(PROGRAM_DSCPL_EXISTS_MSG, programDiscipline.getId())
            );

        if(!findByProgramAndDiscipline(programDiscipline.getProgram(), programDiscipline.getDiscipline()).isEmpty())
            throw new ResourceAlreadyExistsException(
                    String.format(PROGRAM_DSCPL_EXISTS_MSG, programDiscipline.getId()) // TODO - id is always null, print something different
            );

        if(!programService.existsById(programDiscipline.getProgram().getId()))
            throw new ResourceNotFoundException(PROGRAM_NOT_FOUND_MSG);

        if(!disciplineService.existsById(programDiscipline.getDiscipline().getId()))
            throw new ResourceNotFoundException(DSCPL_NOT_FOUND_MSG);

        if(programDiscipline.getAcademicYear() > programDiscipline.getProgram().getEducationPeriod())
            throw new BadResourceException(PROGRAM_DSCPL_INVALID_YEAR_MSG);

        return programDisciplineRepository.save(programDiscipline);
    }

    public ProgramDiscipline updateProgramDiscipline(Long id, ProgramDiscipline programDiscipline) throws ResourceNotFoundException, BadResourceException {
        if(!existsById(id))
            throw new ResourceNotFoundException(
                    String.format(PROGRAM_DSCPL_NOT_FOUND_MSG, id)
            );

        if(!programService.existsById(programDiscipline.getProgram().getId()))
            throw new ResourceNotFoundException(PROGRAM_NOT_FOUND_MSG);

        if(!disciplineService.existsById(programDiscipline.getDiscipline().getId()))
            throw new ResourceNotFoundException(DSCPL_NOT_FOUND_MSG);

        if(programDiscipline.getAcademicYear() > programDiscipline.getProgram().getEducationPeriod())
            throw new BadResourceException(PROGRAM_DSCPL_INVALID_YEAR_MSG);

        programDiscipline.setId(id);
        return programDisciplineRepository.save(programDiscipline);
    }

    public void deleteProgramDiscipline(Long id) throws ResourceNotFoundException {
        if(!existsById(id))
            throw new ResourceNotFoundException(
                    String.format(PROGRAM_DSCPL_NOT_FOUND_MSG, id)
            );

        programDisciplineRepository.deleteById(id);
    }
}
