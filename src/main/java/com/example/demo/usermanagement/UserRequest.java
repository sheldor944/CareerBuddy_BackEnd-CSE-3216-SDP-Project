package com.example.demo.usermanagement;

import lombok.Data;

@Data
public class UserRequest {
    private String name;
    private String email;
    private String password;

}
