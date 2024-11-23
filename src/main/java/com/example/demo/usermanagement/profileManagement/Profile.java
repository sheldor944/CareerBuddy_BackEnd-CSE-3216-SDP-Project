package com.example.demo.usermanagement.profileManagement;

import com.example.demo.usermanagement.models.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "profiles")
@Data
@Setter
@NoArgsConstructor
public class Profile {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private String bio;
    private String email;
    private String phoneNumber;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true)
    private User user;



    public Profile(ProfileRequest profileRequest) {
        this.name = profileRequest.getName();
        this.bio = profileRequest.getBio();
        this.email = profileRequest.getEmail();
        this.phoneNumber = profileRequest.getPhoneNumber();
    }
}
