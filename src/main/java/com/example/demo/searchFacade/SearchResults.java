package com.example.demo.searchFacade;

import com.example.demo.jobmanagement.job.JobDTO;
import com.example.demo.jobmanagement.companymanagement.CompanyDTO;
import lombok.Data;

import java.util.List;

@Data
public class SearchResults {
    private List<JobDTO> jobs;
    private List<CompanyDTO> companies;

    public SearchResults(List<JobDTO> jobs, List<CompanyDTO> companies) {
        this.jobs = jobs;
        this.companies = companies;
    }

    // Getters and setters
}
