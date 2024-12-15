package com.example.demo.usermanagement.profileManagement;

import com.example.demo.usermanagement.models.User;
import com.example.demo.usermanagement.profileManagement.skill.Skill;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
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

    @ManyToMany
    @JoinTable(
            name = "profile_skills", // Intermediary table
            joinColumns = @JoinColumn(name = "profile_id"), // FK to Profile
            inverseJoinColumns = @JoinColumn(name = "skill_id") // FK to Skill
    )
    private Set<Skill> skills;


    public Profile(ProfileRequest profileRequest) {
        this.name = profileRequest.getName();
        this.bio = profileRequest.getBio();
        this.email = profileRequest.getEmail();
        this.phoneNumber = profileRequest.getPhoneNumber();

    }
    public Profile(ProfileRequest profileRequest, Set<Skill> skills) {
        this.name = profileRequest.getName();
        this.bio = profileRequest.getBio();
        this.email = profileRequest.getEmail();
        this.phoneNumber = profileRequest.getPhoneNumber();
        this.skills = skills;

    }

}
