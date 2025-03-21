package com.example.demo.usermanagement;

import lombok.Data;

import java.util.UUID;

@Data
public class UserDTO {
    private String email;
    private UUID id;

    public UserDTO( UUID id , String email) {
        this.id = id;
        this.email = email;
    }
}
