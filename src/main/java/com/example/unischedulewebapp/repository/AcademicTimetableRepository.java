package com.example.unischedulewebapp.repository;

import com.example.unischedulewebapp.model.AcademicTimetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademicTimetableRepository
        extends JpaRepository<AcademicTimetable, Long> {
}
