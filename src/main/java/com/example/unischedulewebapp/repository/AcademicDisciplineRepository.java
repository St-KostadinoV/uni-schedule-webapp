package com.example.unischedulewebapp.repository;

import com.example.unischedulewebapp.model.AcademicDiscipline;
import com.example.unischedulewebapp.repository.generic.AcademicStructureRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademicDisciplineRepository
        extends AcademicStructureRepository<AcademicDiscipline> {
}
