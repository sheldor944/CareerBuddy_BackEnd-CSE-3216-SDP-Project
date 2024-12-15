package com.example.demo.jobmanagement.job;

import com.example.demo.jobmanagement.companymanagement.Company;
import com.example.demo.jobmanagement.companymanagement.CompanyDTO;
import com.example.demo.usermanagement.profileManagement.skill.SkillDTO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
public class JobDTO {
    private UUID id;
    private String title;
    private String description;
    private String location;
    private int experience;
    private String jobType;
    private LocalDateTime deadline;
    private int salary;
    private CompanyDTO company;
    private Set<SkillDTO> skills;

    // Constructor with all fields
    public JobDTO(Job job) {
        this.id = job.getId();
        this.title = job.getTitle();
        this.description = job.getDescription();
        this.location = job.getLocation();
        this.experience = job.getExperience();
        this.jobType = job.getJobType();
        this.deadline = job.getDeadline();
        this.salary = job.getSalary();

    }

    // Constructor explicitly taking Job and Company
    public JobDTO(Job job, Company company) {
        this.id = job.getId();
        this.title = job.getTitle();
        this.description = job.getDescription();
        this.location = job.getLocation();
        this.experience = job.getExperience();
        this.jobType = job.getJobType();
        this.deadline = job.getDeadline();
        this.salary = job.getSalary();
        this.company =  new CompanyDTO(
                company.getName(),
                company.getLocation(),
                company.getPhoneNumber(),
                company.getEmail(),
                company.getDomain(),
                company.getWebsite(),
                company.getDescription(),
                company.getSize(),
                company.getFoundationYear(),
                company.getRegistrationYear(),
                company.isActive(),
                company.getId()
        );
    }
    public JobDTO(Job job, Company company, Set<SkillDTO> skills) {
        this.id = job.getId();
        this.title = job.getTitle();
        this.description = job.getDescription();
        this.location = job.getLocation();
        this.experience = job.getExperience();
        this.jobType = job.getJobType();
        this.deadline = job.getDeadline();
        this.salary = job.getSalary();
        this.skills = skills;
        this.company =  new CompanyDTO(
                company.getName(),
                company.getLocation(),
                company.getPhoneNumber(),
                company.getEmail(),
                company.getDomain(),
                company.getWebsite(),
                company.getDescription(),
                company.getSize(),
                company.getFoundationYear(),
                company.getRegistrationYear(),
                company.isActive(),
                company.getId()
        );
    }
}
