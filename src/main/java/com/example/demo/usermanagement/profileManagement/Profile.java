package com.example.demo.usermanagement.profileManagement;

import com.example.demo.jobmanagement.job.Job;
import com.example.demo.usermanagement.models.User;
import com.example.demo.usermanagement.profileManagement.education.Education;
import com.example.demo.usermanagement.profileManagement.experience.Experience;
import com.example.demo.usermanagement.profileManagement.resume.Resume;
import com.example.demo.usermanagement.profileManagement.skill.Skill;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
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
    private String address;

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

    @ManyToMany
    @JoinTable(
            name = "profile_experiences", // Intermediary table
            joinColumns = @JoinColumn(name = "profile_id"), // FK to Profile
            inverseJoinColumns = @JoinColumn(name = "experience_id") // FK to Experience
    )
    private Set<Experience> experiences;

    @ManyToMany
    @JoinTable(
            name = "profile_educations", // Intermediary table
            joinColumns = @JoinColumn(name = "profile_id"), // FK to Profile
            inverseJoinColumns = @JoinColumn(name = "education_id") // FK to Education
    )
    private Set<Education> educations;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Resume> resumes;

    public Profile(ProfileRequest profileRequest) {
        this.name = profileRequest.getName();
        this.bio = profileRequest.getBio();
        this.email = profileRequest.getEmail();
        this.phoneNumber = profileRequest.getPhoneNumber();
        this.address = profileRequest.getAddress();

    }
    public Profile(ProfileRequest profileRequest, Set<Skill> skills) {
        this.name = profileRequest.getName();
        this.bio = profileRequest.getBio();
        this.email = profileRequest.getEmail();
        this.phoneNumber = profileRequest.getPhoneNumber();
        this.skills = skills;
        this.address = profileRequest.getAddress();

    }

}
