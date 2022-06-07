package com.springboot.blog.payload;

//Razan Yassin
//1182226
public class JWTAuthResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    //Constructor

    public JWTAuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }
    //Getter and Setter
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }
}
