package com.example.demo.jobmanagement;

import com.example.demo.jobmanagement.companymanagement.Company;

import java.time.LocalDateTime;
import java.util.Date;

public class JobBuilder {
    private Company company;
    private String title;
    private String description;
    private String location;
    private double experience;
    private String jobType;
    private LocalDateTime deadline;
    private double salary;

    public JobBuilder company(Company company) {
        this.company = company;
        return this;
    }

    public JobBuilder title(String title) {
        this.title = title;
        return this;
    }

    public JobBuilder description(String description) {
        this.description = description;
        return this;
    }

    public JobBuilder location(String location) {
        this.location = location;
        return this;
    }

    public JobBuilder experience(double experience) {
        this.experience = experience;
        return this;
    }

    public JobBuilder jobType(String jobType) {
        this.jobType = jobType;
        return this;
    }

    public JobBuilder deadline(LocalDateTime deadline) {
        this.deadline = deadline;
        return this;
    }

    public JobBuilder salary(double salary) {
        this.salary = salary;
        return this;
    }

    public Job build() {
        return new Job(company, title, description, location, experience, jobType, deadline, salary);
    }
}
