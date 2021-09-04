package com.example.unischedulewebapp.service;

import com.example.unischedulewebapp.exception.BadResourceException;
import com.example.unischedulewebapp.exception.ResourceAlreadyExistsException;
import com.example.unischedulewebapp.exception.ResourceNotFoundException;
import com.example.unischedulewebapp.model.AcademicDiscipline;
import com.example.unischedulewebapp.model.AcademicProgram;
import com.example.unischedulewebapp.model.ProgramDiscipline;
import com.example.unischedulewebapp.repository.ProgramDisciplineRepo;
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

    private final ProgramDisciplineRepo programDisciplineRepo;
    private final AcademicProgramService programService;
    private final AcademicDisciplineService disciplineService;

    @Autowired
    public ProgramDisciplineService(ProgramDisciplineRepo programDisciplineRepo,
                                    AcademicProgramService programService,
                                    AcademicDisciplineService disciplineService) {
        this.programDisciplineRepo = programDisciplineRepo;
        this.programService = programService;
        this.disciplineService = disciplineService;
    }

    public boolean existsById(Long id){
        return programDisciplineRepo
                .existsById(id);
    }

    public ProgramDiscipline findById(Long id) throws ResourceNotFoundException {
        return programDisciplineRepo
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                String.format(PROGRAM_DSCPL_NOT_FOUND_MSG, id)
                        ));
    }

    public List<ProgramDiscipline> findByProgram(AcademicProgram program) {
        return new ArrayList<>(programDisciplineRepo
                .findByProgram(program));
    }

    public List<ProgramDiscipline> findByDiscipline(AcademicDiscipline discipline) {
        return new ArrayList<>(programDisciplineRepo
                .findByDiscipline(discipline));
    }

    public List<ProgramDiscipline> findByProgramAndDiscipline(AcademicProgram program, AcademicDiscipline discipline) {
        return new ArrayList<>(programDisciplineRepo
                .findByProgramAndDiscipline(program, discipline));
    }

    public List<ProgramDiscipline> findByProgramAndAcademicYear(AcademicProgram program, Integer year) {
        return new ArrayList<>(programDisciplineRepo
                .findByProgramAndAcademicYear(program, year));
    }

    public List<ProgramDiscipline> findAll() {
        return programDisciplineRepo
                .findAll();
    }

    public List<ProgramDiscipline> findAll(Sort sort) {
        return programDisciplineRepo
                .findAll(sort);
    }

    public List<ProgramDiscipline> findAll(int pageNumber, int rowsPerPage) {
        return programDisciplineRepo
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
                    String.format(PROGRAM_DSCPL_EXISTS_MSG, programDiscipline.getProgram().getAbbreviation() + "-" + programDiscipline.getDiscipline().getAbbreviation())
            );

        if(!programService.existsById(programDiscipline.getProgram().getId()))
            throw new ResourceNotFoundException(PROGRAM_NOT_FOUND_MSG);

        if(!disciplineService.existsById(programDiscipline.getDiscipline().getId()))
            throw new ResourceNotFoundException(DSCPL_NOT_FOUND_MSG);

        programDiscipline.setProgram(programService.findById(programDiscipline.getProgram().getId()));
        if(programDiscipline.getAcademicYear() > programDiscipline.getProgram().getEducationPeriod())
            throw new BadResourceException(PROGRAM_DSCPL_INVALID_YEAR_MSG);

        return programDisciplineRepo.save(programDiscipline);
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

        programDiscipline.setProgram(programService.findById(programDiscipline.getProgram().getId()));
        if(programDiscipline.getAcademicYear() > programDiscipline.getProgram().getEducationPeriod())
            throw new BadResourceException(PROGRAM_DSCPL_INVALID_YEAR_MSG);

        programDiscipline.setId(id);
        return programDisciplineRepo.save(programDiscipline);
    }

    public void deleteProgramDiscipline(Long id) throws ResourceNotFoundException {
        if(!existsById(id))
            throw new ResourceNotFoundException(
                    String.format(PROGRAM_DSCPL_NOT_FOUND_MSG, id)
            );

        programDisciplineRepo.deleteById(id);
    }
}
