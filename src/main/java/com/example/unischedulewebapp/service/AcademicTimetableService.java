package com.example.unischedulewebapp.service;

import com.example.unischedulewebapp.exception.ResourceAlreadyExistsException;
import com.example.unischedulewebapp.exception.ResourceNotFoundException;
import com.example.unischedulewebapp.model.AcademicTimetable;
import com.example.unischedulewebapp.repository.AcademicTimetableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcademicTimetableService {

    private final static String TIMETBL_NOT_FOUND_MSG =
            "Timetable %s not found!";
    private final static String TIMETBL_EXISTS_MSG =
            "Timetable %s already exists!";
    private final static String _NOT_FOUND_MSG =
            "Discipline is taught by non-existent teacher!";

    private final AcademicTimetableRepository timetableRepository;
    private final ProgramDisciplineService programDisciplineService;
    private final TeacherService teacherService;

    @Autowired
    public AcademicTimetableService(AcademicTimetableRepository timetableRepository,
                                    ProgramDisciplineService programDisciplineService,
                                    TeacherService teacherService) {
        this.timetableRepository = timetableRepository;
        this.programDisciplineService = programDisciplineService;
        this.teacherService = teacherService;
    }

    public boolean existsById(Long id){
        return timetableRepository
                .existsById(id);
    }

    public AcademicTimetable findById(Long id) throws ResourceNotFoundException {
        return timetableRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                String.format(TIMETBL_NOT_FOUND_MSG, "with id=" + id)
                        ));
    }

    public List<AcademicTimetable> findAll() {
        return timetableRepository
                .findAll();
    }

    public List<AcademicTimetable> findAll(int pageNumber, int rowsPerPage) {
        return timetableRepository
                .findAll(PageRequest.of(pageNumber - 1, rowsPerPage))
                .toList();
    }

    public void addTimetable(AcademicTimetable timetable) throws ResourceAlreadyExistsException, ResourceNotFoundException {
        if(timetable.getId()!=null && existsById(timetable.getId()))
            throw new ResourceAlreadyExistsException(
                    String.format(TIMETBL_EXISTS_MSG, "with id=" + timetable.getId())
            );

        if(!programDisciplineService.existsById(timetable.getProgramDiscipline().getId()))
            throw new ResourceNotFoundException();

        if(!teacherService.existsById(timetable.getAssignedTeacher().getId()))
            throw new ResourceNotFoundException();

        timetableRepository.save(timetable);
    }

    public void updateTimetable(Long id, AcademicTimetable timetable) throws ResourceNotFoundException {
        if(!existsById(id))
            throw new ResourceNotFoundException();

        if(!programDisciplineService.existsById(timetable.getProgramDiscipline().getId()))
            throw new ResourceNotFoundException();

        if(!teacherService.existsById(timetable.getAssignedTeacher().getId()))
            throw new ResourceNotFoundException();

        timetable.setId(id);
        timetableRepository.save(timetable);
    }

    public void deleteTimetable(Long id) throws ResourceNotFoundException {
        if(!existsById(id))
            throw new ResourceNotFoundException();

        timetableRepository.deleteById(id);
    }
}
