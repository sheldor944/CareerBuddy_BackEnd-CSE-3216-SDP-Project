package com.example.demo.usermanagement.profileManagement;

import com.example.demo.usermanagement.UserDTO;
import com.example.demo.usermanagement.profileManagement.education.Education;
import com.example.demo.usermanagement.profileManagement.education.EducationDTO;
import com.example.demo.usermanagement.profileManagement.experience.ExperienceDTO;
import com.example.demo.usermanagement.profileManagement.research.ResearchDTO;
import com.example.demo.usermanagement.profileManagement.skill.Skill;
import com.example.demo.usermanagement.profileManagement.skill.SkillDTO;
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
    private String address;

    private Set<SkillDTO> skillSet;
    private Set<ExperienceDTO> experienceSet;
    private Set<EducationDTO> educationSet;
    private Set<ResearchDTO> researchSet;
    private UserDTO userDTO ;

    public ProfileDTO(UUID id,
                      String name,
                      String bio,
                      String email,
                      String phoneNumber,
                      Set<SkillDTO> skillSet,
                      String address,
                      Set<ExperienceDTO> experienceSet,
                      Set<EducationDTO> educationSet,
                      Set<ResearchDTO> researchSet) {
        this.id = id;
        this.name = name;
        this.bio = bio;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.skillSet = skillSet;
        this.address = address;
        this.experienceSet = experienceSet;
        this.educationSet = educationSet;
        this.researchSet = researchSet;
    }
}
