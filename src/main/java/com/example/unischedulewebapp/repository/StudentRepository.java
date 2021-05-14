package com.example.unischedulewebapp.repository;

import com.example.unischedulewebapp.model.AcademicProgram;
import com.example.unischedulewebapp.model.Student;
import com.example.unischedulewebapp.repository.generic.PersonRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface StudentRepository
        extends PersonRepository<Student> {

    Optional<Student> findByFacultyNumber(String facultyNumber);

    Collection<Student> findByAdmissionStream(Integer stream);

    Collection<Student> findByAcademicProgram(AcademicProgram program);

    Collection<Student> findByAcademicYear(Integer year);

    Collection<Student> findByAcademicProgramAndStudentGroup(AcademicProgram program, Integer group);

    Collection<Student> findByActiveStatus(Boolean activeStatus);
}
