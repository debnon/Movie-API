package com.MovieAPI.model;

import javax.persistence.*;

@Entity
@Table(name="roles")
public class Role {

    @Id
    @Column(name ="role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

}
