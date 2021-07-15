package com.example.unischedulewebapp.model;

import com.example.unischedulewebapp.model.base.AcademicStructure;
import com.fasterxml.jackson.annotation.JsonFilter;

import javax.persistence.Entity;
import javax.persistence.Table;

@JsonFilter("FacultyFilter")
@Entity
@Table(
        name = "faculty"
)
public class AcademicFaculty extends AcademicStructure {
    public AcademicFaculty() {
    }

    public AcademicFaculty(String name,
                           String abbreviation) {
        super(name, abbreviation);
    }
}
