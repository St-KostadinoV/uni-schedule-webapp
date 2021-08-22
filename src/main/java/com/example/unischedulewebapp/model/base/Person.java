package com.example.unischedulewebapp.model.base;

import com.example.unischedulewebapp.auth.UserDetailsImpl;
import com.example.unischedulewebapp.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@MappedSuperclass
public abstract class Person {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @OneToOne
    @JoinColumn(
            name = "user_details_id"
    )
    private User user;

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
            name = "email"
    )
    private String email;

    @Column(
            name = "phone_number"
    )
    private String phone;


    public Person() {
    }

    public Person(User user,
                  String firstName,
                  String middleName,
                  String lastName,
                  String email,
                  String phone) {
        this.user = user;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @JsonIgnore
    public String getFullName() {
        return firstName + " " + middleName + " " + lastName;
    }
}
