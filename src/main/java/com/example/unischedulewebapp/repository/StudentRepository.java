package com.example.unischedulewebapp.repository;

import com.example.unischedulewebapp.model.AcademicProgram;
import com.example.unischedulewebapp.model.Student;
import com.example.unischedulewebapp.model.StudentGroup;
import com.example.unischedulewebapp.repository.base.PersonRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface StudentRepository
        extends PersonRepository<Student> {

    boolean existsByFacultyNumber(String facultyNumber);

    Optional<Student> findByFacultyNumber(String facultyNumber);

    Collection<Student> findByStudentGroup(StudentGroup studentGroup);

    Collection<Student> findByActiveStatus(Boolean activeStatus);
}
