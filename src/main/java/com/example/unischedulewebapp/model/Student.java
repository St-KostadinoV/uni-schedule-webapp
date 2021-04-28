package com.example.unischedulewebapp.model;

import com.example.unischedulewebapp.auth.AppUser;

import javax.persistence.*;

import java.io.Serializable;

import static javax.persistence.FetchType.LAZY;

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

    @Column(
            name = "stream",
            nullable = false
    )
    private Integer admissionStream;

    @ManyToOne(
            fetch = LAZY
    )
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
    private Boolean isActive;

    public Student() {
    }

    public Student(Long id,
                   AppUser userDetails,
                   String firstName,
                   String middleName,
                   String lastName,
                   String email,
                   String phone,
                   String facultyNumber,
                   Integer admissionStream,
                   AcademicProgram academicProgram,
                   Integer academicYear,
                   Integer studentGroup,
                   Boolean isActive) {
        super(id, userDetails, firstName, middleName, lastName, email, phone);
        this.facultyNumber = facultyNumber;
        this.admissionStream = admissionStream;
        this.academicProgram = academicProgram;
        this.academicYear = academicYear;
        this.studentGroup = studentGroup;
        this.isActive = isActive;
    }

    public Student(AppUser userDetails,
                   String firstName,
                   String middleName,
                   String lastName,
                   String email,
                   String phone,
                   String facultyNumber,
                   Integer admissionStream,
                   AcademicProgram academicProgram,
                   Integer academicYear,
                   Integer studentGroup,
                   Boolean isActive) {
        super(userDetails, firstName, middleName, lastName, email, phone);
        this.facultyNumber = facultyNumber;
        this.admissionStream = admissionStream;
        this.academicProgram = academicProgram;
        this.academicYear = academicYear;
        this.studentGroup = studentGroup;
        this.isActive = isActive;
    }

    public Student(String firstName,
                   String middleName,
                   String lastName,
                   String email,
                   String phone,
                   String facultyNumber,
                   Integer admissionStream,
                   AcademicProgram academicProgram,
                   Integer academicYear,
                   Integer studentGroup,
                   Boolean isActive) {
        super(firstName, middleName, lastName, email, phone);
        this.facultyNumber = facultyNumber;
        this.admissionStream = admissionStream;
        this.academicProgram = academicProgram;
        this.academicYear = academicYear;
        this.studentGroup = studentGroup;
        this.isActive = isActive;
    }

    public String getFacultyNumber() {
        return facultyNumber;
    }

    public void setFacultyNumber(String facultyNumber) {
        this.facultyNumber = facultyNumber;
    }

    public Integer getAdmissionStream() {
        return admissionStream;
    }

    public void setAdmissionStream(Integer admissionStream) {
        this.admissionStream = admissionStream;
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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
