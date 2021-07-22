package com.example.unischedulewebapp.model;

import com.fasterxml.jackson.annotation.JsonFilter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@JsonFilter("StudentGroupFilter")
@Entity
@Table(
        name = "student_group"
)
public class StudentGroup {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "program_id",
            nullable = false,
            referencedColumnName = "id"
    )
    private AcademicProgram academicProgram;

    @Column(
            name = "year",
            nullable = false
    )
    private Integer academicYear;

    @Column(
            name = "group_number",
            nullable = false
    )
    private Integer groupNumber;

    public StudentGroup(AcademicProgram academicProgram, Integer academicYear, Integer groupNumber) {
        this.academicProgram = academicProgram;
        this.academicYear = academicYear;
        this.groupNumber = groupNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AcademicProgram getAcademicProgram() {
        return academicProgram;
    }

    public void setAcademicProgram(AcademicProgram academicProgram) {
        this.academicProgram = academicProgram;
    }

    public Integer getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(Integer academicYear) {
        this.academicYear = academicYear;
    }

    public Integer getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(Integer groupNumber) {
        this.groupNumber = groupNumber;
    }
}
