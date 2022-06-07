package com.springboot.blog.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

//Razan Yassin
//1182226
@Setter
@Getter
@Entity
@Table(name = "roles")
public class Role {
    //declare the primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    //declare needed columns
    @Column(length = 60)
    private String name;
}
