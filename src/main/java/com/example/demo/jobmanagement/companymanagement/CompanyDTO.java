package com.example.demo.jobmanagement.companymanagement;

import com.example.demo.jobmanagement.Job;
import com.example.demo.jobmanagement.JobDTO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
public class CompanyDTO {
    private UUID id;
    private String companyName;
    private String location;
    private String phoneNumber;
    private String email;
    private String website;
    private String domain;
    private String description;
    private CompanySize size; // If using an enum, use CompanySize type
    private LocalDateTime foundationYear;
    private String registrationYear;
    private boolean isActive;
    private List<JobDTO> jobs;

    // Consise one
//    public CompanyDTO(UUID id, String name, String domain, String location) {
//        this.id = id;
//        this.companyName = name;
//        this.domain = domain;
//        this.location = location;
//    }

    public CompanyDTO(String companyName, String location, String phoneNumber, String email, String domain, String website, String description, CompanySize size, LocalDateTime foundationYear, String registrationYear, boolean isActive, UUID id) {
        this.companyName = companyName;
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.domain = domain;
        this.website = website;
        this.description = description;
        this.size = size;
        this.foundationYear = foundationYear;
        this.registrationYear = registrationYear;
        this.isActive = isActive;
        this.id = id;
    }

    public CompanyDTO(Company company){
        this.companyName = company.getName();
        this.location = company.getLocation();
        this.phoneNumber = company.getPhoneNumber();
        this.email = company.getEmail();
        this.domain = company.getDomain();
        this.website = company.getWebsite();
        this.description = company.getDescription();
        this.size = company.getSize();
        this.foundationYear = company.getFoundationYear();
        this.registrationYear = company.getRegistrationYear();
        this.isActive = company.isActive();
        this.id = company.getId();
        this.jobs = company.getJobs()
                .stream()
                .map(JobDTO::new)  // Assuming JobDTO has a constructor that takes a Job entity as a parameter
                .collect(Collectors.toList());
    }
}
