package com.springboot.blog.Dto;

import lombok.Data;

import java.util.Date;


//Razan Yassin
//1182226
@Data

public class CustomerDto {
    //table column
    private int cutomerId;
    private String firstName;
    private String lastName;
    private Date bornAt;
}
