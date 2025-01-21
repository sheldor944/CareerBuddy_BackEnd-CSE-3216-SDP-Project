package com.example.demo.usermanagement.profileManagement;

import com.example.demo.usermanagement.profileManagement.education.EducationRequest;
import com.example.demo.usermanagement.profileManagement.experience.ExperienceRequest;
import com.example.demo.usermanagement.profileManagement.research.ResearchRequest;
import com.example.demo.usermanagement.profileManagement.skill.Skill;
import com.example.demo.usermanagement.profileManagement.skill.SkillDTO;
import com.example.demo.usermanagement.profileManagement.skill.SkillRequest;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
public class ProfileRequest {


    private String name;
    private String bio;
    private String email;
    private String phoneNumber;
    private String address;

    private Set<SkillDTO> readySkills = new HashSet<>();

    private Set<SkillRequest> newSkills = new HashSet<>();
    private Set<ExperienceRequest> experiences = new HashSet<>();
    private Set<EducationRequest> educations = new HashSet<>();
    private Set<ResearchRequest> researches = new HashSet<>();

}
