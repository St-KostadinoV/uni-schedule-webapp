package com.example.unischedulewebapp.model;

import com.example.unischedulewebapp.model.base.AcademicStructure;
import com.fasterxml.jackson.annotation.JsonFilter;

import javax.persistence.*;

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

    @Column(
            name = "stream",
            nullable = false
    )
    private Integer academicStream;

    public AcademicProgram() {
    }

    public AcademicProgram(String name,
                           String abbreviation,
                           AcademicDepartment department,
                           Integer academicStream) {
        super(name, abbreviation);
        this.department = department;
        this.academicStream = academicStream;
    }

    public AcademicDepartment getDepartment() {
        return department;
    }

    public void setDepartment(AcademicDepartment department) {
        this.department = department;
    }

    public Integer getAcademicStream() {
        return academicStream;
    }

    public void setAcademicStream(Integer academicStream) {
        this.academicStream = academicStream;
    }
}
