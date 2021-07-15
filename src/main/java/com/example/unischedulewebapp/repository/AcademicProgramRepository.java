package com.example.unischedulewebapp.repository;

import com.example.unischedulewebapp.model.AcademicDepartment;
import com.example.unischedulewebapp.model.AcademicProgram;
import com.example.unischedulewebapp.repository.base.AcademicStructureRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface AcademicProgramRepository
        extends AcademicStructureRepository<AcademicProgram> {

    Collection<AcademicProgram> findByDepartment(AcademicDepartment department);

    Collection<AcademicProgram> findByAcademicStream(Integer stream);
}
