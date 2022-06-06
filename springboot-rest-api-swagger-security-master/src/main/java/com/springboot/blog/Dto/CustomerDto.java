package com.springboot.blog.Dto;

import lombok.Data;

import java.util.Date;


@Data

public class CustomerDto {
    private int cutomerId;
    private String firstName;
    private String lastName;
    private Date bornAt;
}
