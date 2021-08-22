package com.example.unischedulewebapp.repository;

import com.example.unischedulewebapp.model.AcademicDepartment;
import com.example.unischedulewebapp.model.enums.AcademicTitle;
import com.example.unischedulewebapp.model.Instructor;
import com.example.unischedulewebapp.repository.base.PersonRepo;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface InstructorRepo
        extends PersonRepo<Instructor> {

    Collection<Instructor> findByTitle(AcademicTitle title);

    Collection<Instructor> findByDepartment(AcademicDepartment department);
}
