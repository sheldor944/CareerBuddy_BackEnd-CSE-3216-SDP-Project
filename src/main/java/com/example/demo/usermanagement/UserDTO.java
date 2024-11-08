package com.example.demo.usermanagement;

import lombok.Data;

import java.util.UUID;

@Data
public class UserDTO {
    private String email;
    private UUID id;

    public UserDTO(String email, UUID id ) {
        this.email = email;
        this.id = id;
    }
}
