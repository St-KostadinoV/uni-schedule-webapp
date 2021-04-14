package com.example.unischedulewebapp.repository;

import com.example.unischedulewebapp.model.DisciplineTimetable;
import com.example.unischedulewebapp.model.DisciplineTimetableId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplineTimetableRepository
        extends JpaRepository<DisciplineTimetable, DisciplineTimetableId> {
}
