package com.example.unischedulewebapp.repository;

import com.example.unischedulewebapp.model.Student;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository
        extends PersonRepository<Student> {

    Optional<Student> findByFacultyNumber(String facultyNumber);
}
