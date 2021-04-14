package com.example.unischedulewebapp.repository;

import com.example.unischedulewebapp.model.AcademicStructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AcademicStructureRepository
        extends JpaRepository<AcademicStructure, Long> {
}
