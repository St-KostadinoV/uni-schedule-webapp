package com.example.unischedulewebapp.repository;

import com.example.unischedulewebapp.model.AcademicDepartment;
import com.example.unischedulewebapp.model.AcademicFaculty;
import com.example.unischedulewebapp.repository.generic.AcademicStructureRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface AcademicDepartmentRepository
        extends AcademicStructureRepository<AcademicDepartment> {

    Collection<AcademicDepartment> findByFaculty(AcademicFaculty faculty);
}
