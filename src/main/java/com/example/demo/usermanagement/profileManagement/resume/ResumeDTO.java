package com.example.demo.usermanagement.profileManagement.resume;

import com.example.demo.usermanagement.profileManagement.education.EducationDTO;
import com.example.demo.usermanagement.profileManagement.experience.ExperienceDTO;
import com.example.demo.usermanagement.profileManagement.skill.SkillDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class ResumeDTO {
    private String resumeId;
    private String resumeName;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String jobTitle;
    private String summary;
    private String themeColor;

    private String state;

    private Set<EducationDTO> educations;
    private Set<ExperienceDTO> experiences;
    private Set<SkillDTO> skills;



    // This is for update resume
    public ResumeDTO(Resume resume) {
        this.resumeId = resume.getId().toString();
        this.resumeName = resume.getResumeName();
        this.firstName = resume.getFirstName();
        this.lastName = resume.getLastName();
        this.email = resume.getEmail();
        this.phone = resume.getPhoneNumber();
        this.address = resume.getAddress();
        this.jobTitle = resume.getJobTitle();
        this.summary = resume.getSummary();
        this.themeColor = resume.getThemeColor();
        this.educations = EducationDTO.educationSetToEducationDTOSet(resume.getEducations());
        this.experiences = ExperienceDTO.experienceSetToExperienceDTOSet(resume.getExperiences());
        this.skills = SkillDTO.skillSetToSkillDTOSet(resume.getSkills());
        this.state = resume.getState();
    }
}
