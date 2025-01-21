package com.example.demo.usermanagement.profileManagement.experience;

import com.example.demo.usermanagement.profileManagement.Profile;
import com.example.demo.usermanagement.profileManagement.resume.ProfessionalResume;
import com.example.demo.usermanagement.profileManagement.resume.Resume;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "experiences")
@Setter
@Getter
@NoArgsConstructor

public class Experience {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID", updatable = false, nullable = false)
    private UUID id;

    private String title;
    private String companyName;
    private String city;
    private String state;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean currentlyWorking;
    private String workSummary;

    @ManyToMany(mappedBy = "experiences")
    private Set<Profile> profiles;

    @ManyToMany(mappedBy = "experiences")
    private Set<Resume> resumes;

    @ManyToMany(mappedBy = "experiences")
    private Set<ProfessionalResume> professionalResumes;


    public Experience(ExperienceRequest experienceRequest) {
        this.title = experienceRequest.getTitle();
        this.companyName = experienceRequest.getCompanyName();
        this.city = experienceRequest.getCity();
        this.state = experienceRequest.getState();
        this.startDate = experienceRequest.getStartDate();
        this.endDate = experienceRequest.getEndDate();
        this.currentlyWorking = experienceRequest.isCurrentlyWorking();
        this.workSummary = experienceRequest.getWorkSummary();
    }


}
