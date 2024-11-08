package com.example.demo.jobmanagement;

import com.example.demo.jobmanagement.companymanagement.Company;
import com.example.demo.jobmanagement.companymanagement.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class JobService {
    @Autowired
    JobRepository jobRepository;
    @Autowired
    CompanyRepository companyRepository;
    public JobDTO createJob(JobRequest jobRequest){
        UUID companyID = jobRequest.getCompanyId();
        Company company = companyRepository.findById(companyID)
                .orElseThrow(() -> new RuntimeException("Company not found with the id : " + companyID ));

        Job job = new Job(company, jobRequest);
        jobRepository.save(job);

        return new JobDTO(job, company);


    }

    public JobDTO getJobByID(UUID id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found with the id : " + id));
        Company company = job.getCompany();
        return new JobDTO(job, company);
    }

    public List<JobDTO> getAllJobs() {
        List<Job> jobs = jobRepository.findAll();
        return jobs.stream()
                .map(job -> new JobDTO(
                        job,
                        job.getCompany()
                )).collect(Collectors.toList());
    }

    public List<JobDTO> searchJobs(String title, String description) {
        List<Job> jobs = jobRepository.findByMultipleFields(title, description);
        return jobs.stream()
                .map(job -> new JobDTO(
                        job,
                        job.getCompany()
                )).collect(Collectors.toList());
//        return null;
    }
}
