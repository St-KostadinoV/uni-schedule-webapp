package com.example.unischedulewebapp.service;

import com.example.unischedulewebapp.exception.BadResourceException;
import com.example.unischedulewebapp.exception.ResourceAlreadyExistsException;
import com.example.unischedulewebapp.exception.ResourceNotFoundException;
import com.example.unischedulewebapp.exception.TimetableCollisionException;
import com.example.unischedulewebapp.model.AcademicTimetable;
import com.example.unischedulewebapp.model.ProgramDiscipline;
import com.example.unischedulewebapp.model.Student;
import com.example.unischedulewebapp.model.Instructor;
import com.example.unischedulewebapp.model.enums.AcademicClassType;
import com.example.unischedulewebapp.repository.AcademicTimetableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AcademicTimetableService {

    private final static String TIMETBL_NOT_FOUND_MSG =
            "Timetable %s not found!";
    private final static String TIMETBL_EXISTS_MSG =
            "Timetable %s already exists!";
    private final static String TIMETBL_PD_NOT_FOUND_MSG =
            "Timetable includes non-existent program-discipline combination!";
    private final static String TIMETBL_INSTRUCTOR_NOT_FOUND_MSG =
            "Timetable includes non-existent instructor!";
    private final static String TIMETBL_UNAFFILIATED_INSTRUCTOR_MSG =
            "Timetable's assigned instructor is not affiliated with the discipline!";

    private final AcademicTimetableRepository timetableRepository;
    private final ProgramDisciplineService programDisciplineService;
    private final InstructorService instructorService;

    @Autowired
    public AcademicTimetableService(AcademicTimetableRepository timetableRepository,
                                    ProgramDisciplineService programDisciplineService,
                                    InstructorService instructorService) {
        this.timetableRepository = timetableRepository;
        this.programDisciplineService = programDisciplineService;
        this.instructorService = instructorService;
    }

    public boolean existsById(Long id) {
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

    public List<AcademicTimetable> findByAssignedInstructor(Instructor instructor) {
        return new ArrayList<>(timetableRepository
                .findByAssignedInstructor(instructor));
    }

    public List<AcademicTimetable> findByDayOfWeek(DayOfWeek dayOfWeek) {
        return new ArrayList<>(timetableRepository
                .findByDayOfWeek(dayOfWeek));
    }

    public List<AcademicTimetable> findByStartTime(LocalTime startTime) {
        return new ArrayList<>(timetableRepository
                .findByStartTime(startTime));
    }

    public List<AcademicTimetable> findByDesignatedRoom(String room) {
        return new ArrayList<>(timetableRepository
                .findByDesignatedRoom(room));
    }

    public List<AcademicTimetable> findByProgramDiscipline(ProgramDiscipline programDiscipline) {
        return new ArrayList<>(timetableRepository
                .findByProgramDiscipline(programDiscipline));
    }

    public List<AcademicTimetable> findByClassType(AcademicClassType classType) {
        return new ArrayList<>(timetableRepository
                .findByClassType(classType));
    }

    public List<AcademicTimetable> findByProgramDisciplineAndStudentGroup(ProgramDiscipline programDiscipline, Integer year) {
        return new ArrayList<>(timetableRepository
                .findByProgramDisciplineAndStudentGroup(programDiscipline, year));
    }

    public List<AcademicTimetable> findAll() {
        return timetableRepository
                .findAll();
    }

    public List<AcademicTimetable> findAll(Sort sort) {
        return timetableRepository
                .findAll(sort);
    }

    public List<AcademicTimetable> findAll(int pageNumber, int rowsPerPage) {
        return timetableRepository
                .findAll(PageRequest.of(pageNumber - 1, rowsPerPage))
                .toList();
    }

    public List<AcademicTimetable> findInstructorDailySchedule(Instructor instructor) {
        return new ArrayList<>(timetableRepository
                .findByAssignedInstructorAndDayOfWeek(instructor, LocalDate.now().getDayOfWeek()));
    }

    public List<AcademicTimetable> findStudentDailySchedule(Student student) {
        return new ArrayList<>(timetableRepository
                .findStudentDailySchedule(
                        student.getAcademicProgram(),
                        student.getAcademicYear(),
                        student.getStudentGroup(),
                        LocalDate.now().getDayOfWeek()
                ));
    }

    public List<AcademicTimetable> findStudentWeeklySchedule(Student student) {
        return new ArrayList<>(timetableRepository
                .findStudentWeeklySchedule(
                        student.getAcademicProgram(),
                        student.getAcademicYear(),
                        student.getStudentGroup()
                ));
    }

    public AcademicTimetable addTimetable(AcademicTimetable timetable) throws ResourceAlreadyExistsException, ResourceNotFoundException, BadResourceException, TimetableCollisionException {
        if(timetable.getId() != null && existsById(timetable.getId()))
            throw new ResourceAlreadyExistsException(
                    String.format(TIMETBL_EXISTS_MSG, "with id=" + timetable.getId())
            );

        if(!programDisciplineService.existsById(timetable.getProgramDiscipline().getId()))
            throw new ResourceNotFoundException(TIMETBL_PD_NOT_FOUND_MSG);

        if(!instructorService.existsById(timetable.getAssignedInstructor().getId()))
            throw new ResourceNotFoundException(TIMETBL_INSTRUCTOR_NOT_FOUND_MSG);

        if(timetable.getProgramDiscipline().getDiscipline().getLeadingInstructor() != timetable.getAssignedInstructor()
                && !timetable.getProgramDiscipline().getDiscipline().getAssistingInstructors().contains(timetable.getAssignedInstructor()))
            throw new BadResourceException(TIMETBL_UNAFFILIATED_INSTRUCTOR_MSG);

        isTimetableValid(timetable);

        return timetableRepository.save(timetable);
    }

    public AcademicTimetable updateTimetable(Long id, AcademicTimetable timetable) throws ResourceNotFoundException, BadResourceException, TimetableCollisionException {
        if(!existsById(id))
            throw new ResourceNotFoundException(
                    String.format(TIMETBL_NOT_FOUND_MSG, "with id=" + id)
            );

        if(!programDisciplineService.existsById(timetable.getProgramDiscipline().getId()))
            throw new ResourceNotFoundException(TIMETBL_PD_NOT_FOUND_MSG);

        if(!instructorService.existsById(timetable.getAssignedInstructor().getId()))
            throw new ResourceNotFoundException(TIMETBL_INSTRUCTOR_NOT_FOUND_MSG);

        if(timetable.getProgramDiscipline().getDiscipline().getLeadingInstructor() != timetable.getAssignedInstructor()
                && !timetable.getProgramDiscipline().getDiscipline().getAssistingInstructors().contains(timetable.getAssignedInstructor()))
            throw new BadResourceException(TIMETBL_UNAFFILIATED_INSTRUCTOR_MSG);

        isTimetableValid(timetable);

        timetable.setId(id);
        return timetableRepository.save(timetable);
    }

    public void deleteTimetable(Long id) throws ResourceNotFoundException {
        if(!existsById(id))
            throw new ResourceNotFoundException(
                    String.format(TIMETBL_NOT_FOUND_MSG, "with id=" + id)
            );

        timetableRepository.deleteById(id);
    }

    private void isTimetableValid(AcademicTimetable timetable) throws TimetableCollisionException {
        if (!timetableRepository
                .findInstructorAvailability(
                        timetable.getAssignedInstructor(),
                        timetable.getDayOfWeek(),
                        timetable.getStartTime(),
                        timetable.getEndTime())
                .isEmpty())
            throw new TimetableCollisionException("Instructor is not available during this time period of the day!");

        if (timetableRepository
                .findStudentsAvailability(
                        timetable.getProgramDiscipline().getProgram(),
                        timetable.getProgramDiscipline().getAcademicYear(),
                        timetable.getStudentGroup(),
                        timetable.getDayOfWeek(),
                        timetable.getStartTime(),
                        timetable.getEndTime())
                .isEmpty())
            throw new TimetableCollisionException("Students are not available during this time period of the day!");

        if (timetableRepository
                .findRoomAvailability(
                        timetable.getDesignatedRoom(),
                        timetable.getDayOfWeek(),
                        timetable.getStartTime(),
                        timetable.getEndTime())
                .isEmpty())
            throw new TimetableCollisionException("Room is not available during this time period of the day!");

        if (timetableRepository
                .findClassDuplicates(
                        timetable.getProgramDiscipline(),
                        timetable.getStudentGroup(),
                        timetable.getClassType())
                .isEmpty())
            throw new TimetableCollisionException("Students already have this class during the week!");
    }
}
