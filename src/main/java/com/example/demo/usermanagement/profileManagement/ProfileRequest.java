package com.example.demo.usermanagement.profileManagement;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

import java.util.UUID;

@Data
public class ProfileRequest {

    @NotNull(message = "User ID is required")
    private UUID user_id;
    private String name;
    private String bio;
    private String email;
    private String phoneNumber;
}
