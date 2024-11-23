package com.example.demo.usermanagement.profileManagement;

import com.example.demo.usermanagement.UserDTO;
import lombok.Data;

import java.util.UUID;

@Data
public class ProfileDTO {
    private UUID id;

    private String name;
    private String bio;
    private String email;
    private String phoneNumber;

    private UserDTO userDTO ;

    public ProfileDTO(UUID id, String name, String bio, String email, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.bio = bio;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
