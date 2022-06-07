package com.springboot.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//Razan Yassin
//1182226
@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "comments")
public class Comment {
    //declare the primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    //declare needed columns
    private String name;
    private String email;
    private String body;
    //Defining the many-to-one relationship between the comment and the post
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;
}
