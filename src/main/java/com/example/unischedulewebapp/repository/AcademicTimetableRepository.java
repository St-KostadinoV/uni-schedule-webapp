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
public interface AcademicTimetableRepository
        extends JpaRepository<AcademicTimetable, Long> {

    Collection<AcademicTimetable> findByAssignedInstructor(Instructor instructor);

    Collection<AcademicTimetable> findByAssignedInstructorAndDayOfWeek(Instructor instructor, DayOfWeek dayOfWeek);

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
}
