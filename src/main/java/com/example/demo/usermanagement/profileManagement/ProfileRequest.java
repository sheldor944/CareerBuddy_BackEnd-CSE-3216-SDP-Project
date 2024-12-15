package com.example.demo.usermanagement.profileManagement;

import com.example.demo.usermanagement.profileManagement.skill.Skill;
import com.example.demo.usermanagement.profileManagement.skill.SkillDTO;
import com.example.demo.usermanagement.profileManagement.skill.SkillRequest;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

import java.util.Set;
import java.util.UUID;

@Data
public class ProfileRequest {

    @NotNull(message = "User ID is required")
    private UUID user_id;
    private String name;
    private String bio;
    private String email;
    private String phoneNumber;

    private Set<SkillDTO> readySkills;

    private Set<SkillRequest> newSkills;

}
