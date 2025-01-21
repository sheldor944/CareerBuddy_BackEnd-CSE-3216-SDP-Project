package com.example.demo.usermanagement.profileManagement.resume;

import com.example.demo.usermanagement.profileManagement.education.EducationDTO;
import com.example.demo.usermanagement.profileManagement.experience.ExperienceDTO;
import com.example.demo.usermanagement.profileManagement.skill.SkillDTO;
import lombok.Data;
import lombok.Getter;

import java.util.Set;
import java.util.UUID;


@Data
@Getter
public class ResumeRequest {

    private String resumeType;
    private String resumeName;
    private String jobTitle;
    private String summary;
    private String themeColor;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;

    private Set<UUID> educations;
    private Set<UUID> experiences;
    private Set<UUID> skills;
    private Set<UUID> researches;

}
