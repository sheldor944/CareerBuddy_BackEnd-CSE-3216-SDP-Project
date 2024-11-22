package com.example.demo.jobmanagement.companymanagement;

import com.example.demo.jobmanagement.Job;

import java.time.LocalDateTime;
import java.util.List;



public class CompanyBuilder {



    private String name;
    private String location;
    private String phoneNumber;
    private String email;
    private String website;
    private String domain;
    private String description;
    private CompanySize size;
    private LocalDateTime foundationYear;
    private boolean isActive;
    private List<Job> jobs;
    private String registrationYear;

    public CompanyBuilder name(String name) {
        this.name = name;
        return this;
    }

    public CompanyBuilder location(String location) {
        this.location = location;
        return this;
    }

    public CompanyBuilder phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public CompanyBuilder email(String email) {
        this.email = email;
        return this;
    }

    public CompanyBuilder website(String website) {
        this.website = website;
        return this;
    }

    public CompanyBuilder domain(String domain) {
        this.domain = domain;
        return this;
    }

    public CompanyBuilder description(String description) {
        this.description = description;
        return this;
    }

    public CompanyBuilder size(CompanySize size) {
        this.size = size;
        return this;
    }

    public CompanyBuilder foundationYear(LocalDateTime foundationYear) {
        this.foundationYear = foundationYear;
        return this;
    }

    public CompanyBuilder isActive(boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public CompanyBuilder jobs(List<Job> jobs) {
        this.jobs = jobs;
        return null;
    }

    public CompanyBuilder registrationYear(String registrationYear) {
        this.registrationYear = registrationYear;
        return this;
    }

    public Company build() {
        return new Company(name, location, phoneNumber, email, website, registrationYear, jobs,isActive, foundationYear, size, domain, description);
    }

    public CompanyBuilder fromRequest(CompanyRequest companyRequest) {
        this.name = companyRequest.getCompanyName();
        this.location = companyRequest.getLocation();
        this.phoneNumber = companyRequest.getPhoneNumber();
        this.email = companyRequest.getEmail();
        this.website = companyRequest.getWebsite();
        this.domain = companyRequest.getDomain();
        this.description = companyRequest.getDescription();
        this.size = companyRequest.getSize();
        this.foundationYear = companyRequest.getFoundationYear();
        this.isActive = companyRequest.isActive();
        this.registrationYear = companyRequest.getRegistrationYear();
        return this;
    }

//    public Company build() {
//        // Create a CompanyRequest to pass to the Company constructor
//        CompanyRequest companyRequest = new CompanyRequest(name, location, domain);
//        Company company = new Company(companyRequest);
//        company.setJobs(jobs);
//        return company;
//    }
}

