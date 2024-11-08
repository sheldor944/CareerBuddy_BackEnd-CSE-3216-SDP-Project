package com.example.demo.usermanagement.authentication;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthenticationRequest implements Serializable {


    private String email;
    private String password;

    //need default constructor for JSON Parsing
    public AuthenticationRequest()
    {

    }

    public AuthenticationRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}