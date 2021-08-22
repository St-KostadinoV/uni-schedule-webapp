package com.example.unischedulewebapp.repository;

import com.example.unischedulewebapp.model.AcademicDepartment;
import com.example.unischedulewebapp.model.AcademicFaculty;
import com.example.unischedulewebapp.repository.base.AcademicStructureRepo;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface AcademicDepartmentRepo
        extends AcademicStructureRepo<AcademicDepartment> {

    Collection<AcademicDepartment> findByFaculty(AcademicFaculty faculty);
}
