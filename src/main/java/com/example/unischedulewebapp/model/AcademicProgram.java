package com.example.unischedulewebapp.model;

import com.example.unischedulewebapp.model.base.AcademicStructure;
import com.example.unischedulewebapp.model.enums.AcademicDegree;
import com.fasterxml.jackson.annotation.JsonFilter;

import javax.persistence.*;
import java.io.Serializable;

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
            name = "academic_stream",
            nullable = false
    )
    private Integer academicStream;

    @Column(
            name = "degree",
            nullable = false
    )
    private AcademicDegree degree;

    @Column(
            name = "education_period",
            nullable = false
    )
    private Float educationPeriod;

    public AcademicProgram() {
    }

    public AcademicProgram(String name,
                           String abbreviation,
                           AcademicDepartment department,
                           Integer academicStream,
                           AcademicDegree degree,
                           Float educationPeriod) {
        super(name, abbreviation);
        this.department = department;
        this.academicStream = academicStream;
        this.degree = degree;
        this.educationPeriod = educationPeriod;
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

    public AcademicDegree getDegree() {
        return degree;
    }

    public void setDegree(AcademicDegree degree) {
        this.degree = degree;
    }

    public Float getEducationPeriod() {
        return educationPeriod;
    }

    public void setEducationPeriod(Float educationPeriod) {
        this.educationPeriod = educationPeriod;
    }
}
