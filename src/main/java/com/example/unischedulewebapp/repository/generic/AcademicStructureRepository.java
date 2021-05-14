package com.example.unischedulewebapp.repository.generic;

import com.example.unischedulewebapp.model.generic.AcademicStructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AcademicStructureRepository<T extends AcademicStructure>
        extends JpaRepository<T, Long> {


}
