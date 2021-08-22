package com.example.unischedulewebapp.repository;

import com.example.unischedulewebapp.model.AcademicDepartment;
import com.example.unischedulewebapp.model.AcademicProgram;
import com.example.unischedulewebapp.model.enums.AcademicDegree;
import com.example.unischedulewebapp.repository.base.AcademicStructureRepo;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface AcademicProgramRepo
        extends AcademicStructureRepo<AcademicProgram> {

    Collection<AcademicProgram> findByDepartment(AcademicDepartment department);

    Collection<AcademicProgram> findByAcademicStream(Integer stream);

    Collection<AcademicProgram> findByDegree(AcademicDegree degree);
}
