package com.example.unischedulewebapp.repository;

import com.example.unischedulewebapp.model.AcademicDepartment;
import com.example.unischedulewebapp.model.AcademicTitle;
import com.example.unischedulewebapp.model.Teacher;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface TeacherRepository
        extends PersonRepository<Teacher> {

    Collection<Teacher> findByTitle(AcademicTitle title);

    Collection<Teacher> findByDepartment(AcademicDepartment department);
}
