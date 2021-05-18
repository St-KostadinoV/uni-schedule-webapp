package com.example.unischedulewebapp.model;

import com.example.unischedulewebapp.auth.AppUser;
import com.example.unischedulewebapp.model.enums.AcademicTitle;
import com.example.unischedulewebapp.model.generic.Person;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;

@JsonFilter("TeacherFilter")
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
    private AcademicTitle title;

    @ManyToOne
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
    private Boolean honoraryStatus;

    public Teacher() {
    }

    public Teacher(Long id,
                   AppUser userDetails,
                   String firstName,
                   String middleName,
                   String lastName,
                   String email,
                   String phone,
                   AcademicTitle title,
                   AcademicDepartment department,
                   String office,
                   Boolean honoraryStatus) {
        super(id, userDetails, firstName, middleName, lastName, email, phone);
        this.title = title;
        this.department = department;
        this.office = office;
        this.honoraryStatus = honoraryStatus;
    }

    public Teacher(AppUser userDetails,
                   String firstName,
                   String middleName,
                   String lastName,
                   String email,
                   String phone,
                   AcademicTitle title,
                   AcademicDepartment department,
                   String office,
                   Boolean honoraryStatus) {
        super(userDetails, firstName, middleName, lastName, email, phone);
        this.title = title;
        this.department = department;
        this.office = office;
        this.honoraryStatus = honoraryStatus;
    }

    public Teacher(String firstName,
                   String middleName,
                   String lastName,
                   String email,
                   String phone,
                   AcademicTitle title,
                   AcademicDepartment department,
                   String office,
                   Boolean honoraryStatus) {
        super(firstName, middleName, lastName, email, phone);
        this.title = title;
        this.department = department;
        this.office = office;
        this.honoraryStatus = honoraryStatus;
    }

    public AcademicTitle getTitle() {
        return title;
    }

    public void setTitle(AcademicTitle title) {
        this.title = title;
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

    public Boolean getHonoraryStatus() {
        return honoraryStatus;
    }

    public void setHonoraryStatus(Boolean honoraryStatus) {
        this.honoraryStatus = honoraryStatus;
    }

    @JsonIgnore
    public String getFullNameWithTitle(){
        return title.getTitle() + " " + super.getFullName();
    }

}
