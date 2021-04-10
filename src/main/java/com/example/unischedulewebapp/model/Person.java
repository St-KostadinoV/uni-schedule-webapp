package com.example.unischedulewebapp.model;

import com.example.unischedulewebapp.auth.AppUser;

import javax.persistence.*;

@MappedSuperclass
public abstract class Person {

    @Id
    @OneToOne
    @JoinColumn(
            name = "user_details_id",
            updatable = false,
            referencedColumnName = "id"
    )
    private AppUser userDetails;

    @Column(
            name = "first_name",
            nullable = false
    )
    private String firstName;

    @Column(
            name = "middle_name",
            nullable = false
    )
    private String middleName;

    @Column(
            name = "last_name",
            nullable = false
    )
    private String lastName;

    @Column(
            name = "email",
            nullable = false
    )
    private String email;

    public Person() {
    }

    public Person(AppUser userDetails,
                  String firstName,
                  String middleName,
                  String lastName,
                  String email) {
        this.userDetails = userDetails;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
    }

    public Person(String firstName,
                  String middleName,
                  String lastName,
                  String email) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
    }

    public AppUser getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(AppUser userDetails) {
        this.userDetails = userDetails;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return firstName + " " + middleName + " " + lastName;
    }
}
