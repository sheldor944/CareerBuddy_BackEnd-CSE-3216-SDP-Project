package com.example.demo.usermanagement.profileManagement.resume;

import com.example.demo.usermanagement.profileManagement.Profile;
import com.example.demo.usermanagement.profileManagement.education.Education;
import com.example.demo.usermanagement.profileManagement.experience.Experience;
import com.example.demo.usermanagement.profileManagement.research.Research;
import com.example.demo.usermanagement.profileManagement.skill.Skill;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ProfessionalResume {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID", updatable = false, nullable = false)
    private UUID id;

    private String resumeName;
    private String jobTitle;
    private String summary;
    private String themeColor;

    // In my opinion thsee should not be there
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @ManyToMany
    @JoinTable(
            name = "professional_resume_educations", // Intermediary table
            joinColumns = @JoinColumn(name = "resume_id"), // FK to Resume
            inverseJoinColumns = @JoinColumn(name = "education_id") // FK to Education
    )
    private Set<Education> educations;
    @ManyToMany
    @JoinTable(
            name = "professional_resume_experiences", // Intermediary table
            joinColumns = @JoinColumn(name = "resume_id"), // FK to Resume
            inverseJoinColumns = @JoinColumn(name = "experience_id") // FK to Experience
    )
    private Set<Experience> experiences;
    @ManyToMany
    @JoinTable(
            name = "professional_resume_skills", // Intermediary table
            joinColumns = @JoinColumn(name = "resume_id"), // FK to Resume
            inverseJoinColumns = @JoinColumn(name = "skill_id") // FK to Skill
    )
    private Set<Skill> skills;

    @ManyToMany
    @JoinTable(
            name = "professional_resume_researches",
            joinColumns = @JoinColumn(name = "resume_id"),
            inverseJoinColumns = @JoinColumn(name = "research_id")
    )
    private Set<Research> researches;

    public ProfessionalResume(ResumeRequest resumeRequest) {
        this.resumeName = resumeRequest.getResumeName();
        this.jobTitle = resumeRequest.getJobTitle();
        this.summary = resumeRequest.getSummary();
        this.themeColor = resumeRequest.getThemeColor();
        this.firstName = resumeRequest.getFirstName();
        this.lastName = resumeRequest.getLastName();
        this.email = resumeRequest.getEmail();
        this.phoneNumber = resumeRequest.getPhoneNumber();
        this.address = resumeRequest.getAddress();
    }



}
