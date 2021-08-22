package com.example.unischedulewebapp.repository;

import com.example.unischedulewebapp.model.AcademicDiscipline;
import com.example.unischedulewebapp.model.AcademicProgram;
import com.example.unischedulewebapp.model.ProgramDiscipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ProgramDisciplineRepo
        extends JpaRepository<ProgramDiscipline, Long> {

    Collection<ProgramDiscipline> findByProgram(AcademicProgram program);

    Collection<ProgramDiscipline> findByDiscipline(AcademicDiscipline discipline);

    Collection<ProgramDiscipline> findByProgramAndDiscipline(AcademicProgram program, AcademicDiscipline discipline);

    Collection<ProgramDiscipline> findByProgramAndAcademicYear(AcademicProgram program, Integer year);

}
