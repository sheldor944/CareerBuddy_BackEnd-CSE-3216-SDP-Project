package com.example.demo.jobmanagement.job;

import com.example.demo.jobmanagement.companymanagement.Company;
import com.example.demo.jobmanagement.companymanagement.CompanyRepository;
import com.example.demo.jobmanagement.jobApplication.JobApplication;
import com.example.demo.jobmanagement.jobApplication.JobApplicationDTO;
import com.example.demo.searchFacade.SearchCriteria;
import com.example.demo.usermanagement.models.User;
import com.example.demo.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class JobService {
    @Autowired
    JobRepository jobRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    UserRepository userRepository;

    public JobDTO createJob(JobRequest jobRequest) {
        UUID companyID = jobRequest.getCompanyId();
        Company company = companyRepository.findById(companyID)
                .orElseThrow(() -> new RuntimeException("Company not found with the id : " + companyID));


        Job job = new JobBuilder()
                .company(company)
                .title(jobRequest.getTitle())
                .description(jobRequest.getDescription())
                .location(jobRequest.getLocation())
                .experience(jobRequest.getExperience())
                .jobType(jobRequest.getJobType())
                .deadline(jobRequest.getDeadline())
                .salary(jobRequest.getSalary())
                .build();

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

    public List<JobDTO> searchJobs(SearchCriteria criteria) {
        return jobRepository.findByMultipleFields(
                criteria.getJobTitle(),
                criteria.getLocation(),
                criteria.getMinExperience(),
                criteria.getJobType(),
                criteria.getMinSalary()
        ).stream().map(JobDTO::new).collect(Collectors.toList());
    }

//    public List<JobDTO> searchJobs(String title, String description, String location,
//                                   int experience, String jobType,  int salary) {
//
//
//        List<Job> jobs = jobRepository.findByMultipleFields(title,location,experience,jobType, salary);
//        return jobs.stream()
//                .map(job -> new JobDTO(job))
//                .collect(Collectors.toList());
//    }
//    public List<JobDTO> searchJobs(String title, String description) {
//        List<Job> jobs = jobRepository.findByMultipleFields(title, description);
//        return jobs.stream()
//                .map(job -> new JobDTO(
//                        job,
//                        job.getCompany()
//                )).collect(Collectors.toList());
//    }

//        return null
//}


}
