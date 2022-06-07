package com.springboot.blog.payload;

import java.util.Date;

//Razan Yassin
//1182226
public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;
    //Constructor

    public ErrorDetails(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }
    //Getters
    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
