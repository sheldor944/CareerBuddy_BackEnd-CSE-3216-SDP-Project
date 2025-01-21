package com.example.demo.usermanagement.profileManagement.education;


import com.example.demo.usermanagement.profileManagement.Profile;
import com.example.demo.usermanagement.profileManagement.resume.ProfessionalResume;
import com.example.demo.usermanagement.profileManagement.resume.Resume;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "educations")
@Setter
@Getter
@NoArgsConstructor
public class Education {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID", updatable = false, nullable = false)
    private UUID id;

    private String universityName;
    private String degree;
    private String major;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToMany(mappedBy = "educations")
    private Set<Profile> profiles;

    @ManyToMany(mappedBy = "educations")
    private Set<Resume> resumes;

    @ManyToMany(mappedBy = "educations")
    private Set<ProfessionalResume> professionalResumes;

    public Education(EducationRequest educationRequest) {
        this.universityName = educationRequest.getUniversityName();
        this.degree = educationRequest.getDegree();
        this.major = educationRequest.getMajor();
        this.description = educationRequest.getDescription();
        this.startDate = educationRequest.getStartDate();
        this.endDate = educationRequest.getEndDate();
    }
}
