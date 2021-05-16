package com.example.unischedulewebapp.model;

import com.example.unischedulewebapp.auth.AppUser;
import com.example.unischedulewebapp.model.generic.Person;

import javax.persistence.*;

import java.io.Serializable;

import static javax.persistence.FetchType.EAGER;

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
                   Boolean activeStatus) {
        super(id, userDetails, firstName, middleName, lastName, email, phone);
        this.facultyNumber = facultyNumber;
        this.admissionStream = admissionStream;
        this.academicProgram = academicProgram;
        this.academicYear = academicYear;
        this.studentGroup = studentGroup;
        this.activeStatus = activeStatus;
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
                   Boolean activeStatus) {
        super(userDetails, firstName, middleName, lastName, email, phone);
        this.facultyNumber = facultyNumber;
        this.admissionStream = admissionStream;
        this.academicProgram = academicProgram;
        this.academicYear = academicYear;
        this.studentGroup = studentGroup;
        this.activeStatus = activeStatus;
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
                   Boolean activeStatus) {
        super(firstName, middleName, lastName, email, phone);
        this.facultyNumber = facultyNumber;
        this.admissionStream = admissionStream;
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

    public Boolean getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(Boolean activeStatus) {
        this.activeStatus = activeStatus;
    }
}
