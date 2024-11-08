package com.example.demo.usermanagement.models;

import com.example.demo.rolemanagement.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID", updatable = false, nullable = false)
    private UUID id;
    @Column(nullable = false, unique = true)
    private String email;

    private String password;
    private boolean active;
    private String userRoles;

    private boolean verified;

    public User(String email, String password){
        this.email = email;
        this.password = password;
        this.userRoles = "ROLE_admin";
        this.active = true;

    }


}