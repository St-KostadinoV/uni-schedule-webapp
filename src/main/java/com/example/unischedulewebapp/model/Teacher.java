package com.example.unischedulewebapp.model;

import com.example.unischedulewebapp.auth.AppUser;

import javax.persistence.*;

import java.io.Serializable;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;

@Entity
@Table(
        name = "teacher"
)
public class Teacher extends Person implements Serializable {

    @Enumerated(value = STRING)
    @Column(
            name = "title",
            nullable = false
    )
    private AcademicTitle academicTitle;

    @ManyToOne(
            fetch = LAZY
    )
    @JoinColumn(
            name = "department_id",
            nullable = false,
            referencedColumnName = "id"
    )
    private AcademicDepartment department;

    @Column(
            name = "office"
    )
    private String office;

    @Column(
            name = "honorary",
            nullable = false
    )
    private Boolean isHonorary;

    public Teacher() {
    }

    public Teacher(Long id,
                   AppUser userDetails,
                   String firstName,
                   String middleName,
                   String lastName,
                   String email,
                   String phone,
                   AcademicTitle academicTitle,
                   AcademicDepartment department,
                   String office,
                   Boolean isHonorary) {
        super(id, userDetails, firstName, middleName, lastName, email, phone);
        this.academicTitle = academicTitle;
        this.department = department;
        this.office = office;
        this.isHonorary = isHonorary;
    }

    public Teacher(AppUser userDetails,
                   String firstName,
                   String middleName,
                   String lastName,
                   String email,
                   String phone,
                   AcademicTitle academicTitle,
                   AcademicDepartment department,
                   String office,
                   Boolean isHonorary) {
        super(userDetails, firstName, middleName, lastName, email, phone);
        this.academicTitle = academicTitle;
        this.department = department;
        this.office = office;
        this.isHonorary = isHonorary;
    }

    public Teacher(String firstName,
                   String middleName,
                   String lastName,
                   String email,
                   String phone,
                   AcademicTitle academicTitle,
                   AcademicDepartment department,
                   String office,
                   Boolean isHonorary) {
        super(firstName, middleName, lastName, email, phone);
        this.academicTitle = academicTitle;
        this.department = department;
        this.office = office;
        this.isHonorary = isHonorary;
    }

    public AcademicTitle getAcademicTitle() {
        return academicTitle;
    }

    public void setAcademicTitle(AcademicTitle academicTitle) {
        this.academicTitle = academicTitle;
    }

    public AcademicDepartment getDepartment() {
        return department;
    }

    public void setDepartment(AcademicDepartment department) {
        this.department = department;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public Boolean getHonorary() {
        return isHonorary;
    }

    public void setHonorary(Boolean honorary) {
        isHonorary = honorary;
    }

    public String getFullNameWithTitle(){
        return academicTitle.getTitle() + " " + super.getFullName();
    }

}
