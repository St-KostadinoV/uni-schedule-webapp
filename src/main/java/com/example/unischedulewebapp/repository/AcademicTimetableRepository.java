package com.example.unischedulewebapp.repository;

import com.example.unischedulewebapp.model.AcademicTimetable;
import com.example.unischedulewebapp.model.ProgramDiscipline;
import com.example.unischedulewebapp.model.Teacher;
import com.example.unischedulewebapp.model.enums.AcademicClassType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Collection;

@Repository
public interface AcademicTimetableRepository
        extends JpaRepository<AcademicTimetable, Long> {

    Collection<AcademicTimetable> findByAssignedTeacher(Teacher teacher);

    Collection<AcademicTimetable> findByDayOfWeek(DayOfWeek dayOfWeek);

    Collection<AcademicTimetable> findByStartTime(LocalTime startTime);

    Collection<AcademicTimetable> findByDesignatedRoom(String room);

    Collection<AcademicTimetable> findByProgramDiscipline(ProgramDiscipline programDiscipline);

    Collection<AcademicTimetable> findByClassType(AcademicClassType classType);

    Collection<AcademicTimetable> findByProgramDisciplineAndStudentGroup(ProgramDiscipline programDiscipline, Integer studentGroup);
}
