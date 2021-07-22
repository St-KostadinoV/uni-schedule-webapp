package com.example.unischedulewebapp.repository;

import com.example.unischedulewebapp.model.AcademicProgram;
import com.example.unischedulewebapp.model.StudentGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface StudentGroupRepository
        extends JpaRepository<StudentGroup, Long> {

    Collection<StudentGroup> findByAcademicProgram(AcademicProgram program);

    Collection<StudentGroup> findByAcademicYear(Integer year);

    Collection<StudentGroup> findByAcademicProgramAndAcademicYear(AcademicProgram program, Integer year);

    Collection<StudentGroup> findByAcademicProgramAndAcademicYearAndGroupNumber(AcademicProgram program, Integer year, Integer groupNumber);
}
