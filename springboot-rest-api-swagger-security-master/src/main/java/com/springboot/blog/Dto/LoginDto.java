package com.springboot.blog.Dto;

import lombok.Data;

//Razan Yassin
//1182226
@Data
public class LoginDto {
    //table column
    private String usernameOrEmail;
    private String password;
}
