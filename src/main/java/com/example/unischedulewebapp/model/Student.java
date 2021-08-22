package com.example.unischedulewebapp.model;

import com.example.unischedulewebapp.auth.UserDetailsImpl;
import com.example.unischedulewebapp.model.base.Person;
import com.fasterxml.jackson.annotation.JsonFilter;

import javax.persistence.*;

import java.io.Serializable;

@JsonFilter("StudentFilter")
@Entity
@Table(
        name = "student"
)
public class Student extends Person implements Serializable {

    @Column(
            name = "fac_number",
            nullable = false,
            unique = true
    )
    private String facultyNumber;

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
            name = "student_group"
    )
    private Integer studentGroup;

    @Column(
            name = "active",
            nullable = false
    )
    private Boolean activeStatus;

    public Student() {
    }

    public Student(User user,
                   String firstName,
                   String middleName,
                   String lastName,
                   String email,
                   String phone,
                   String facultyNumber,
                   AcademicProgram academicProgram,
                   Integer academicYear,
                   Integer studentGroup,
                   Boolean activeStatus) {
        super(user, firstName, middleName, lastName, email, phone);
        this.facultyNumber = facultyNumber;
        this.academicProgram = academicProgram;
        this.academicYear = academicYear;
        this.studentGroup = studentGroup;
        this.activeStatus = activeStatus;
    }

    public String getFacultyNumber() {
        return facultyNumber;
    }

    public void setFacultyNumber(String facultyNumber) {
        this.facultyNumber = facultyNumber;
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

    public Integer getStudentGroup() {
        return studentGroup;
    }

    public void setStudentGroup(Integer studentGroup) {
        this.studentGroup = studentGroup;
    }

    public Boolean getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(Boolean activeStatus) {
        this.activeStatus = activeStatus;
    }
}
