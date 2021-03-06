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
import com.example.unischedulewebapp.repository.AcademicTimetableRepo;
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

    private final AcademicTimetableRepo timetableRepository;
    private final ProgramDisciplineService programDisciplineService;
    private final InstructorService instructorService;

    @Autowired
    public AcademicTimetableService(AcademicTimetableRepo timetableRepository,
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

    public AcademicTimetable addTimetable(AcademicTimetable timetable)
            throws ResourceAlreadyExistsException, ResourceNotFoundException, BadResourceException, TimetableCollisionException {
        if(timetable.getId() != null && existsById(timetable.getId()))
            throw new ResourceAlreadyExistsException(
                    String.format(TIMETBL_EXISTS_MSG, "with id=" + timetable.getId())
            );

        timetable.setAssignedInstructor(instructorService.findById(timetable.getAssignedInstructor().getId()));

        timetable.setProgramDiscipline(programDisciplineService.findById(timetable.getProgramDiscipline().getId()));
        boolean isLeadingInstructorAssigned = timetable
                .getProgramDiscipline()
                .getDiscipline()
                .getLeadingInstructor()
                .equals(timetable.getAssignedInstructor());
        boolean isAssistingInstructorAssigned = timetable
                .getProgramDiscipline()
                .getDiscipline()
                .getAssistingInstructors()
                .contains(timetable.getAssignedInstructor());
        if(!isLeadingInstructorAssigned && !isAssistingInstructorAssigned)
            throw new BadResourceException(TIMETBL_UNAFFILIATED_INSTRUCTOR_MSG);

        isTimetableValid(timetable);

        return timetableRepository.save(timetable);
    }

    public AcademicTimetable updateTimetable(Long id, AcademicTimetable timetable) throws ResourceNotFoundException, BadResourceException, TimetableCollisionException {
        if(!existsById(id))
            throw new ResourceNotFoundException(
                    String.format(TIMETBL_NOT_FOUND_MSG, "with id=" + id)
            );

        timetable.setAssignedInstructor(instructorService.findById(timetable.getAssignedInstructor().getId()));

        timetable.setProgramDiscipline(programDisciplineService.findById(timetable.getProgramDiscipline().getId()));
        boolean isLeadingInstructorAssigned = timetable
                .getProgramDiscipline()
                .getDiscipline()
                .getLeadingInstructor()
                .equals(timetable.getAssignedInstructor());
        boolean isAssistingInstructorAssigned = timetable
                .getProgramDiscipline()
                .getDiscipline()
                .getAssistingInstructors()
                .contains(timetable.getAssignedInstructor());
        if(!isLeadingInstructorAssigned && !isAssistingInstructorAssigned)
            throw new BadResourceException(TIMETBL_UNAFFILIATED_INSTRUCTOR_MSG);

        timetable.setId(id);
        isTimetableValid(timetable);

        return timetableRepository.save(timetable);
    }

    public void deleteTimetable(Long id) throws ResourceNotFoundException {
        if(!existsById(id))
            throw new ResourceNotFoundException(
                    String.format(TIMETBL_NOT_FOUND_MSG, "with id=" + id)
            );

        timetableRepository.deleteById(id);
    }

    private final static String INSTRUCTOR_NOT_AVAILABLE = "Instructor is not available during this time period of the day!";
    private final static String STUDENT_NOT_AVAILABLE = "Students are not available during this time period of the day!";
    private final static String ROOM_NOT_AVAILABLE = "Room is not available during this time period of the day!";
    private final static String CLASS_DUPLICATE = "Students already have this class during the week!";

    private void isTimetableValid(AcademicTimetable timetable) throws TimetableCollisionException {
        List<AcademicTimetable> t = new ArrayList<>(timetableRepository.findInstructorAvailability(
                timetable.getAssignedInstructor(),
                timetable.getDayOfWeek(),
                timetable.getStartTime(),
                timetable.getEndTime()));
        if (!t.isEmpty())
            if(timetable.getId() == null || !timetable.getId().equals(t.get(0).getId()))
                throw new TimetableCollisionException(INSTRUCTOR_NOT_AVAILABLE);

        t = new ArrayList<>(timetableRepository.findStudentsAvailability(
                timetable.getProgramDiscipline().getProgram(),
                timetable.getProgramDiscipline().getAcademicYear(),
                timetable.getStudentGroup(),
                timetable.getDayOfWeek(),
                timetable.getStartTime(),
                timetable.getEndTime()));
        if (!t.isEmpty())
            if(timetable.getId() == null || !timetable.getId().equals(t.get(0).getId()))
                throw new TimetableCollisionException(STUDENT_NOT_AVAILABLE);

        t = new ArrayList<>(timetableRepository.findRoomAvailability(
                timetable.getDesignatedRoom(),
                timetable.getDayOfWeek(),
                timetable.getStartTime(),
                timetable.getEndTime()));
        if (!t.isEmpty())
            if(timetable.getId() == null || !timetable.getId().equals(t.get(0).getId()))
                throw new TimetableCollisionException(ROOM_NOT_AVAILABLE);

        t = new ArrayList<>(timetableRepository.findClassDuplicates(
                timetable.getProgramDiscipline(),
                timetable.getStudentGroup(),
                timetable.getClassType()));
        if (!t.isEmpty())
            if(timetable.getId() == null || !timetable.getId().equals(t.get(0).getId()))
                throw new TimetableCollisionException(CLASS_DUPLICATE);
    }
}
