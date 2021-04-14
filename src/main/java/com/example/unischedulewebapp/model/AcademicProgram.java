package com.example.unischedulewebapp.model;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(
        name = "program"
)
public class AcademicProgram extends AcademicStructure {

    @ManyToOne(
            fetch = LAZY
    )
    @JoinColumn(
            name = "department_id",
            nullable = false,
            referencedColumnName = "id"
    )
    private AcademicDepartment department;

    public AcademicProgram() {
    }

    public AcademicProgram(Long id,
                           String name,
                           String abbreviation,
                           AcademicDepartment department) {
        super(id, name, abbreviation);
        this.department = department;
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