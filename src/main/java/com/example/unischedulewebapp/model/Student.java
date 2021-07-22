package com.example.unischedulewebapp.model;

import com.example.unischedulewebapp.auth.AppUser;
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
            name = "student_group_id",
            nullable = false,
            referencedColumnName = "id"
    )
    private StudentGroup studentGroup;

    @Column(
            name = "active",
            nullable = false
    )
    private Boolean activeStatus;

    public Student() {
    }

    public Student(AppUser userDetails,
                   String firstName,
                   String middleName,
                   String lastName,
                   String email,
                   String phone,
                   String facultyNumber,
                   StudentGroup studentGroup,
                   Boolean activeStatus) {
        super(userDetails, firstName, middleName, lastName, email, phone);
        this.facultyNumber = facultyNumber;
        this.studentGroup = studentGroup;
        this.activeStatus = activeStatus;
    }

    public String getFacultyNumber() {
        return facultyNumber;
    }

    public void setFacultyNumber(String facultyNumber) {
        this.facultyNumber = facultyNumber;
    }

    public StudentGroup getStudentGroup() {
        return studentGroup;
    }

    public void setStudentGroup(StudentGroup studentGroup) {
        this.studentGroup = studentGroup;
    }

    public Boolean getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(Boolean activeStatus) {
        this.activeStatus = activeStatus;
    }
}
