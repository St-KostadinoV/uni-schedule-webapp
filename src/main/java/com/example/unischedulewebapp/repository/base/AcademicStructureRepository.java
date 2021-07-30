package com.example.unischedulewebapp.repository.base;

import com.example.unischedulewebapp.model.base.AcademicStructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Collection;

@NoRepositoryBean
public interface AcademicStructureRepository<T extends AcademicStructure>
        extends JpaRepository<T, Long> {

    Collection<T> findByName(String name);

    Collection<T> findByAbbreviation(String abbreviation);
}
