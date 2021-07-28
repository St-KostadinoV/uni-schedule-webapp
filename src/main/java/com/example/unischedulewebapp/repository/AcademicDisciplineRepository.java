package com.example.unischedulewebapp.repository;

import com.example.unischedulewebapp.model.AcademicDepartment;
import com.example.unischedulewebapp.model.AcademicDiscipline;
import com.example.unischedulewebapp.model.Instructor;
import com.example.unischedulewebapp.repository.base.AcademicStructureRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface AcademicDisciplineRepository
        extends AcademicStructureRepository<AcademicDiscipline> {

    Collection<AcademicDiscipline> findByDepartment(AcademicDepartment department);

    Collection<AcademicDiscipline> findByLeadingInstructor(Instructor instructor);

    // TODO - rework
    //Collection<AcademicDiscipline> findByTeacher(Teacher teacher);
}
