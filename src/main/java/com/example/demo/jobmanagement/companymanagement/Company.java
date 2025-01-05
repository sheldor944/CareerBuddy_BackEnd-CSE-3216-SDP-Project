package com.example.demo.jobmanagement.companymanagement;

import com.example.demo.jobmanagement.job.Job;
import com.example.demo.notification.*;
import com.example.demo.usermanagement.models.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "companies")
@Data
@NoArgsConstructor
public class Company  {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID", updatable = false, nullable = false)
    private UUID id;
    private String name ;

    private String location;
    private String phoneNumber;
    private String email;
    private String website;

    private String domain;
    private String description;
    @Enumerated(EnumType.STRING) // Store enum as a string in the database
    private CompanySize size;

    private LocalDateTime foundationYear;
    private String registrationYear;
    private boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Job> jobs;




    public Company(CompanyRequest companyRequest){
        this.name = companyRequest.getCompanyName();
        this.domain = companyRequest.getDomain();
        this.location = companyRequest.getLocation();
    }

    public Company(String name, String location, String phoneNumber, String email, String website, String registrationYear, List<Job> jobs, boolean isActive, LocalDateTime foundationYear, CompanySize size, String domain, String description) {
        this.name = name;
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.website = website;
        this.registrationYear = registrationYear;
        this.jobs = jobs;
        this.isActive = isActive;
        this.foundationYear = foundationYear;
        this.size = size;
        this.domain = domain;
        this.description = description;
    }
}
