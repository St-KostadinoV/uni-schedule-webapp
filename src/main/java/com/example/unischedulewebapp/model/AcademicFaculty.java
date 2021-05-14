package com.example.unischedulewebapp.model;

import com.example.unischedulewebapp.model.generic.AcademicStructure;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(
        name = "faculty"
)
public class AcademicFaculty extends AcademicStructure {
    public AcademicFaculty() {
    }

    public AcademicFaculty(Long id,
                           String name,
                           String abbreviation) {
        super(id, name, abbreviation);
    }

    public AcademicFaculty(String name,
                           String abbreviation) {
        super(name, abbreviation);
    }
}
