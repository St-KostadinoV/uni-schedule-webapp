package com.example.unischedulewebapp.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "department")
public class AcademicDepartment extends AcademicStructure {

    public AcademicDepartment() {
    }

    public AcademicDepartment(Long id,
                              String name,
                              String abbreviation) {
        super(id, name, abbreviation);
    }

    public AcademicDepartment(String name,
                              String abbreviation) {
        super(name, abbreviation);
    }

}
