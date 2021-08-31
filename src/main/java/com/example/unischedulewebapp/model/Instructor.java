package com.example.unischedulewebapp.model;

import com.example.unischedulewebapp.model.enums.AcademicDegree;
import com.example.unischedulewebapp.model.enums.AcademicTitle;
import com.example.unischedulewebapp.model.base.Person;
import com.example.unischedulewebapp.model.enums.ProfessionalQualification;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

import static javax.persistence.EnumType.STRING;

@JsonFilter("InstructorFilter")
@Entity
@Table(
        name = "instructor"
)
public class Instructor extends Person implements Serializable {

    @Enumerated(value = STRING)
    @Column(
            name = "title",
            nullable = false
    )
    private AcademicTitle title;

    @Column(
            name = "degree"
    )
    private AcademicDegree degree;

    @Column(
            name = "qualification"
    )
    private ProfessionalQualification qualification;

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

    public Instructor() {
    }

    public Instructor(User user,
                      String firstName,
                      String middleName,
                      String lastName,
                      String email,
                      String phone,
                      AcademicTitle title,
                      AcademicDepartment department,
                      String office,
                      Boolean honoraryStatus) {
        super(user, firstName, middleName, lastName, email, phone);
        this.title = title;
        this.department = department;
        this.office = office;
        this.honoraryStatus = honoraryStatus;
    }

    public Instructor(User user,
                      String firstName,
                      String middleName,
                      String lastName,
                      String email,
                      String phone,
                      AcademicTitle title,
                      AcademicDegree degree,
                      AcademicDepartment department,
                      String office,
                      Boolean honoraryStatus) {
        super(user, firstName, middleName, lastName, email, phone);
        this.title = title;
        this.degree = degree;
        this.department = department;
        this.office = office;
        this.honoraryStatus = honoraryStatus;
    }

    public Instructor(User user,
                      String firstName,
                      String middleName,
                      String lastName,
                      String email,
                      String phone,
                      AcademicTitle title,
                      ProfessionalQualification qualification,
                      AcademicDepartment department,
                      String office,
                      Boolean honoraryStatus) {
        super(user, firstName, middleName, lastName, email, phone);
        this.title = title;
        this.qualification = qualification;
        this.department = department;
        this.office = office;
        this.honoraryStatus = honoraryStatus;
    }

    public Instructor(User user,
                      String firstName,
                      String middleName,
                      String lastName,
                      String email,
                      String phone,
                      AcademicTitle title,
                      AcademicDegree degree,
                      ProfessionalQualification qualification,
                      AcademicDepartment department,
                      String office,
                      Boolean honoraryStatus) {
        super(user, firstName, middleName, lastName, email, phone);
        this.title = title;
        this.degree = degree;
        this.qualification = qualification;
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

    public AcademicDegree getDegree() {
        return degree;
    }

    public ProfessionalQualification getQualification() {
        return qualification;
    }

    public void setQualification(ProfessionalQualification qualification) {
        this.qualification = qualification;
    }

    public void setDegree(AcademicDegree degree) {
        this.degree = degree;
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
        return title.getName() + " " + super.getFullName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instructor that = (Instructor) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}
