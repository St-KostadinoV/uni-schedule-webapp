package com.example.unischedulewebapp.repository;

import com.example.unischedulewebapp.model.ProgramDiscipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramDisciplineRepository
        extends JpaRepository<ProgramDiscipline, Long> {
}
