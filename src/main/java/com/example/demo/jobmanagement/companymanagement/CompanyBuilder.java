package com.example.demo.jobmanagement.companymanagement;

import com.example.demo.jobmanagement.Job;

import java.util.List;


public class CompanyBuilder {
    private String name;
    private String location;
    private String domain;
    private List<Job> jobs;

    public CompanyBuilder name(String name) {
        this.name = name;
        return this;
    }

    public CompanyBuilder location(String location) {
        this.location = location;
        return this;
    }

    public CompanyBuilder domain(String domain) {
        this.domain = domain;
        return this;
    }

    public CompanyBuilder jobs(List<Job> jobs) {
        this.jobs = jobs;
        return this;
    }

    public Company build() {
        // Create a CompanyRequest to pass to the Company constructor
        CompanyRequest companyRequest = new CompanyRequest(name, location, domain);
        Company company = new Company(companyRequest);
        company.setJobs(jobs);
        return company;
    }
}

