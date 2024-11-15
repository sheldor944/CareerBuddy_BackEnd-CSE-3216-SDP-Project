package com.example.demo.jobmanagement;

import com.example.demo.jobmanagement.companymanagement.Company;

public class JobBuilder {
    private Company company;
    private String title;
    private String description;

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

    public Job build() {
        return new Job(company, title, description);
    }
}
