package com.example.unischedulewebapp.model;

import com.example.unischedulewebapp.model.generic.AcademicStructure;
import com.fasterxml.jackson.annotation.JsonFilter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@JsonFilter("ProgramFilter")
@Entity
@Table(
        name = "program"
)
public class AcademicProgram extends AcademicStructure {

    @ManyToOne
    @JoinColumn(
            name = "department_id",
            nullable = false,
            referencedColumnName = "id"
    )
    private AcademicDepartment department;

    public AcademicProgram() {
    }

    public AcademicProgram(String name,
                           String abbreviation,
                           AcademicDepartment department) {
        super(name, abbreviation);
        this.department = department;
    }

    public AcademicDepartment getDepartment() {
        return department;
    }

    public void setDepartment(AcademicDepartment department) {
        this.department = department;
    }
}
