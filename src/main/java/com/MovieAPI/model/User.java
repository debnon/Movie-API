package com.MovieAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    Long id;

    @Column
    boolean superUser;

    @Column
    String username;

    @Column
    //@JsonIgnore
    String password;

    @Column
    String firstname;

    @Column
    String lastname;

    @Column
    String emailID;

    @Column
    String contactnumber;

}
