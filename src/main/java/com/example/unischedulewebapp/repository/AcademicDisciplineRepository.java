package com.example.unischedulewebapp.repository;

import com.example.unischedulewebapp.model.AcademicDiscipline;
import com.example.unischedulewebapp.model.Teacher;
import com.example.unischedulewebapp.repository.generic.AcademicStructureRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface AcademicDisciplineRepository
        extends AcademicStructureRepository<AcademicDiscipline> {

    Collection<AcademicDiscipline> findByLeadingTeacher(Teacher teacher);
}
