package com.example.demo.usermanagement.profileManagement;

import com.example.demo.usermanagement.UserDTO;
import com.example.demo.usermanagement.profileManagement.skill.Skill;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class ProfileDTO {
    private UUID id;

    private String name;
    private String bio;
    private String email;
    private String phoneNumber;

    private Set<Skill> skillSet;
    private UserDTO userDTO ;

    public ProfileDTO(UUID id, String name, String bio, String email, String phoneNumber, Set<Skill> skillSet) {
        this.id = id;
        this.name = name;
        this.bio = bio;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.skillSet = skillSet;
    }
}
