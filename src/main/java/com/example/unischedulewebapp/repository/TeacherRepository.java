package com.example.unischedulewebapp.repository;

import com.example.unischedulewebapp.model.AcademicDepartment;
import com.example.unischedulewebapp.model.enums.AcademicTitle;
import com.example.unischedulewebapp.model.Teacher;
import com.example.unischedulewebapp.repository.generic.PersonRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface TeacherRepository
        extends PersonRepository<Teacher> {

    Collection<Teacher> findByTitle(AcademicTitle title);

    Collection<Teacher> findByDepartment(AcademicDepartment department);
}
