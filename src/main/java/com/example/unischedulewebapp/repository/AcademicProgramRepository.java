package com.example.unischedulewebapp.repository;

import com.example.unischedulewebapp.model.AcademicDepartment;
import com.example.unischedulewebapp.model.AcademicProgram;
import com.example.unischedulewebapp.repository.generic.AcademicStructureRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface AcademicProgramRepository
        extends AcademicStructureRepository<AcademicProgram> {

    Collection<AcademicProgram> findByDepartment(AcademicDepartment department);
}
