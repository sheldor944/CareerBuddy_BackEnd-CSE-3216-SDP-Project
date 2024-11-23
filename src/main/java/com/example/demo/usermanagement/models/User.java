package com.example.demo.usermanagement.models;


import com.example.demo.usermanagement.profileManagement.Profile;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String name;

    private String email;

    private String password;

    private boolean active;

    private String userRoles;

    private boolean verified;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Profile profile;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.userRoles = "ROLE_admin";
        this.active = true;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
        profile.setUser(this); // Establish bidirectional relationship
    }
}
