package com.example.unischedulewebapp.repository;

import com.example.unischedulewebapp.model.AcademicProgram;
import com.example.unischedulewebapp.repository.generic.AcademicStructureRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademicProgramRepository
        extends AcademicStructureRepository<AcademicProgram> {
}
