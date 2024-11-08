package com.example.demo.jobmanagement.companymanagement;

import com.example.demo.jobmanagement.Job;
import com.example.demo.jobmanagement.JobDTO;
import lombok.Data;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
public class CompanyDTO {
    private UUID id ;
    private String companyName;
    private String location;
    private String domain;

    private List<JobDTO> jobs;

    // Consise one
    public CompanyDTO(UUID id, String name, String domain, String location) {
        this.id = id;
        this.companyName = name;
        this.domain = domain;
        this.location = location;
    }

    public CompanyDTO(Company company){
        this.id = company.getId();
        this.companyName = company.getName();
        this.location = company.getLocation();
        this.domain = company.getDomain();
        this.jobs = company.getJobs()
                .stream()
                .map(JobDTO::new)  // Assuming JobDTO has a constructor that takes a Job entity as a parameter
                .collect(Collectors.toList());
    }
}
