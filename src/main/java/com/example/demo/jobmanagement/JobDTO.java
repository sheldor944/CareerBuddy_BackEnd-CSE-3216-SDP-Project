package com.example.demo.jobmanagement;

import com.example.demo.jobmanagement.companymanagement.Company;
import com.example.demo.jobmanagement.companymanagement.CompanyDTO;
import lombok.Data;

import java.util.UUID;

@Data
public class JobDTO {
    private UUID id;
    private String tittle;
    private String description;
    private CompanyDTO company;

    public JobDTO(Job job){
        this.id = job.getId();
        this.tittle = job.getTitle();
        this.description = job.getDescription();
    }

    public JobDTO(Job job, Company company){
        this.id = job.getId();
        this.tittle = job.getTitle();
        this.description = job.getDescription();
        this.company = new CompanyDTO(
                company.getId(),
                company.getName(),
                company.getDomain(),
                company.getLocation()
        );
    }

}
