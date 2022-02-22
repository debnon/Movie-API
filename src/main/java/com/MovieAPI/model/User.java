package com.MovieAPI.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class User {

//    public User(String username, String password, String firstname, String lastname, String emailID, String contactNumber) {
//        this.username = username;
//        this.password = password;
//        this.firstname = firstname;
//        this.lastname = lastname;
//        this.emailID = emailID;
//        this.contactNumber = contactNumber;
//    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    Long id;

    @Column
    boolean superUser;

    @Column
    String username;

    @Column
    String password;

    @Column
    String firstname;

    @Column
    String lastname;

    @Column
    String emailID;

    @Column
    String contactNumber;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmailID() {
        return emailID;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
