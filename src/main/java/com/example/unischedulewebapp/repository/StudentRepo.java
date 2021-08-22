package com.example.unischedulewebapp.repository;

import com.example.unischedulewebapp.model.AcademicProgram;
import com.example.unischedulewebapp.model.Student;
import com.example.unischedulewebapp.repository.base.PersonRepo;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface StudentRepo
        extends PersonRepo<Student> {

    boolean existsByFacultyNumber(String facultyNumber);

    Optional<Student> findByFacultyNumber(String facultyNumber);

    Collection<Student> findByAcademicProgram(AcademicProgram program);

    Collection<Student> findByAcademicYear(Integer year);

    Collection<Student> findByAcademicProgramAndAcademicYear(AcademicProgram program, Integer year);

    Collection<Student> findByAcademicProgramAndAcademicYearAndStudentGroup(AcademicProgram program, Integer year, Integer group);

    Collection<Student> findByActiveStatus(Boolean activeStatus);
}
