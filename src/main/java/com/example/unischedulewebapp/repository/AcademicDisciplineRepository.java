package com.example.unischedulewebapp.repository;

import com.example.unischedulewebapp.model.AcademicDepartment;
import com.example.unischedulewebapp.model.AcademicDiscipline;
import com.example.unischedulewebapp.model.Teacher;
import com.example.unischedulewebapp.repository.base.AcademicStructureRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface AcademicDisciplineRepository
        extends AcademicStructureRepository<AcademicDiscipline> {

    Collection<AcademicDiscipline> findByDepartment(AcademicDepartment department);

    Collection<AcademicDiscipline> findByLeadingTeacher(Teacher teacher);

    // TODO - rework
    //Collection<AcademicDiscipline> findByTeacher(Teacher teacher);
}
