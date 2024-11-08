package com.example.demo.usermanagement;

import lombok.Data;

@Data
public class AuthDTO {
    String token;
    public AuthDTO(String token){
        this.token = token ;
    }
}
