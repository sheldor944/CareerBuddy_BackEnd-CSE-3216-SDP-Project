package com.example.demo.jobmanagement;

import com.example.demo.jobmanagement.companymanagement.Company;
import com.example.demo.jobmanagement.companymanagement.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class JobService {
    @Autowired
    JobRepository jobRepository;
    @Autowired
    CompanyRepository companyRepository;
    public Job createJob(JobRequest jobRequest){
        UUID companyID = jobRequest.getCompanyId();
        Company company = companyRepository.findById(companyID)
                .orElseThrow(() -> new RuntimeException("Company not found with the id : " + companyID ));
        Job job = new Job(company, jobRequest);
        return jobRepository.save(job);


    }
}
