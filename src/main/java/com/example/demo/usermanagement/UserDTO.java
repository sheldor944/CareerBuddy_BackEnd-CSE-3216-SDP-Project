package com.example.demo.usermanagement;

import lombok.Data;

import java.util.UUID;

@Data
public class UserDTO {
    private String email;
    private UUID id;
    private String name;

    public UserDTO( UUID id , String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }
}
