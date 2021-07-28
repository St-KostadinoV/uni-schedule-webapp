package com.example.unischedulewebapp.repository;

import com.example.unischedulewebapp.model.AcademicDepartment;
import com.example.unischedulewebapp.model.enums.AcademicTitle;
import com.example.unischedulewebapp.model.Instructor;
import com.example.unischedulewebapp.repository.base.PersonRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface InstructorRepository
        extends PersonRepository<Instructor> {

    Collection<Instructor> findByTitle(AcademicTitle title);

    Collection<Instructor> findByDepartment(AcademicDepartment department);
}
