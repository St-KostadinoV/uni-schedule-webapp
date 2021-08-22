package com.example.unischedulewebapp.repository;

import com.example.unischedulewebapp.model.AcademicProgram;
import com.example.unischedulewebapp.model.AcademicTimetable;
import com.example.unischedulewebapp.model.ProgramDiscipline;
import com.example.unischedulewebapp.model.Instructor;
import com.example.unischedulewebapp.model.enums.AcademicClassType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Collection;

@Repository
public interface AcademicTimetableRepo
        extends JpaRepository<AcademicTimetable, Long> {

    Collection<AcademicTimetable> findByAssignedInstructor(Instructor instructor);

    Collection<AcademicTimetable> findByAssignedInstructorAndDayOfWeek(Instructor instructor,
                                                                       DayOfWeek dayOfWeek);

    Collection<AcademicTimetable> findByDayOfWeek(DayOfWeek dayOfWeek);

    Collection<AcademicTimetable> findByStartTime(LocalTime startTime);

    Collection<AcademicTimetable> findByDesignatedRoom(String room);

    Collection<AcademicTimetable> findByProgramDiscipline(ProgramDiscipline programDiscipline);

    Collection<AcademicTimetable> findByClassType(AcademicClassType classType);

    Collection<AcademicTimetable> findByProgramDisciplineAndStudentGroup(ProgramDiscipline programDiscipline,
                                                                         Integer studentGroup);

    @Query(
            "select tt from AcademicTimetable tt " +
            "join tt.programDiscipline pd " +
            "where pd.program = :program " +
            "and pd.academicYear = :year " +
            "and tt.studentGroup = :group"
    )
    Collection<AcademicTimetable> findStudentWeeklySchedule(@Param("program") AcademicProgram program,
                                                            @Param("year") Integer year,
                                                            @Param("group") Integer group);

    @Query(
            "select tt from AcademicTimetable tt " +
            "join tt.programDiscipline pd " +
            "where pd.program = :program " +
            "and pd.academicYear = :year " +
            "and tt.studentGroup = :group " +
            "and tt.dayOfWeek = :day"
    )
    Collection<AcademicTimetable> findStudentDailySchedule(@Param("program") AcademicProgram program,
                                                           @Param("year") Integer year,
                                                           @Param("group") Integer group,
                                                           @Param("day") DayOfWeek day);

    // TODO - convert return data type to Optional<>

    @Query(
            "select tt from AcademicTimetable tt " +
            "where tt.assignedInstructor = :instructor " +
            "and tt.dayOfWeek = :dayOfWeek " +
            "and ( " +
            "(:startTime <= tt.startTime and tt.startTime <= :endTime) " +
            "or (:startTime <= tt.endTime and tt.endTime <= :endTime) " +
            ")"
    )
    Collection<AcademicTimetable> findInstructorAvailability(@Param("instructor") Instructor instructor,
                                                             @Param("dayOfWeek") DayOfWeek dayOfWeek,
                                                             @Param("startTime") LocalTime startTime,
                                                             @Param("endTime") LocalTime endTime);

    @Query(
            "select tt from AcademicTimetable tt " +
            "join tt.programDiscipline pd " +
            "where pd.program = :program " +
            "and pd.academicYear = :year " +
            "and tt.studentGroup = :group " +
            "and tt.dayOfWeek = :day " +
            "and ( " +
            "(:startTime <= tt.startTime and tt.startTime <= :endTime) " +
            "or (:startTime <= tt.endTime and tt.endTime <= :endTime) " +
            ")"
    )
    Collection<AcademicTimetable> findStudentsAvailability(@Param("program") AcademicProgram program,
                                                           @Param("year") Integer year,
                                                           @Param("group") Integer group,
                                                           @Param("day") DayOfWeek day,
                                                           @Param("startTime") LocalTime startTime,
                                                           @Param("endTime") LocalTime endTime);

    @Query(
            "select tt from AcademicTimetable tt " +
            "where tt.designatedRoom = :room " +
            "and tt.dayOfWeek = :dayOfWeek " +
            "and ( " +
            "(:startTime <= tt.startTime and tt.startTime <= :endTime) " +
            "or (:startTime <= tt.endTime and tt.endTime <= :endTime) " +
            ")"
    )
    Collection<AcademicTimetable> findRoomAvailability(@Param("room") String room,
                                                       @Param("dayOfWeek") DayOfWeek dayOfWeek,
                                                       @Param("startTime") LocalTime startTime,
                                                       @Param("endTime") LocalTime endTime);

    @Query(
            "select tt from AcademicTimetable tt " +
            "where tt.programDiscipline = :programDiscipline " +
            "and tt.studentGroup = :group " +
            "and tt.classType = :classType"
    )
    Collection<AcademicTimetable> findClassDuplicates(@Param("programDiscipline") ProgramDiscipline programDiscipline,
                                                      @Param("group") Integer group,
                                                      @Param("classType") AcademicClassType classType);
}
