package com.example.demo.jobmanagement.jobApplication;

import com.example.demo.jobmanagement.companymanagement.CompanyRepository;
import com.example.demo.jobmanagement.job.Job;
import com.example.demo.jobmanagement.job.JobRepository;
import com.example.demo.usermanagement.models.User;
import com.example.demo.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class JobApplicationService {

    @Autowired
    JobRepository jobRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    JobApplicationRepository jobApplicationRepository;


    public JobApplicationDTO applyForJob(UUID jobId, UUID userId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found with the id : " + jobId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with the id : " + userId));
        LocalDateTime currentDateTime = LocalDateTime.now();


        JobApplication jobApplication = new JobApplication(job, user, "Pending", currentDateTime, currentDateTime);    // Create a new JobApplication
        jobApplicationRepository.save(jobApplication);
        return new JobApplicationDTO(jobApplication);
    }

    public JobApplicationDTO updateJobApplication(UUID jobApplicationId, JobApplicationRequest jobApplicationRequest) {
        JobApplication jobApplication = jobApplicationRepository.findById(jobApplicationId)
                .orElseThrow(() -> new RuntimeException("Job Application not found with the id : " + jobApplicationId));
        jobApplication.setStatus(jobApplicationRequest.getStatus());
        jobApplication.setUpdatedAt(LocalDateTime.now());
        jobApplicationRepository.save(jobApplication);
        return new JobApplicationDTO(jobApplication);
    }

    public List<JobApplicationDTO> getAllJobApplications() {
        List<JobApplication> jobApplications = jobApplicationRepository.findAll();
        return jobApplications.stream().map(JobApplicationDTO::new).toList();
    }

    public List<JobApplicationDTO> getJobApplicationsByJobId(UUID jobId) {
        List<JobApplication> jobApplications = jobApplicationRepository.findByJobId(jobId);
        return jobApplications.stream().map(JobApplicationDTO::new).toList();
    }

    public List<JobApplicationDTO> getJobApplicationsByCompanyId(UUID companyId) {
        List<Job> jobs = jobRepository.findByCompanyId(companyId);
        List<JobApplication> totalApplications = new ArrayList<>();
        for (Job job : jobs) {
            totalApplications.addAll(jobApplicationRepository.findByJobId(job.getId()));
        }
        return totalApplications.stream().map(JobApplicationDTO::new).toList();
    }

    public JobApplicationDTO getJobApplicationById(UUID jobApplicationId) {
        JobApplication jobApplication = jobApplicationRepository.findById(jobApplicationId)
                .orElseThrow(() -> new RuntimeException("Job Application not found with the id : " + jobApplicationId));
        return new JobApplicationDTO(jobApplication);
    }

    public List<JobApplicationDTO> getJobApplicationsByUserId(UUID userId) {
        List<JobApplication> jobApplications = jobApplicationRepository.findByUserId(userId);
        return jobApplications.stream().map(JobApplicationDTO::new).toList();
    }

    public List<JobApplicationDTO> getJobApplicationsByFilter(String status, LocalDateTime appliedAt, LocalDateTime updatedAt, boolean isGreaterThan) {
        List<JobApplication> jobApplications = jobApplicationRepository.findByFilter(status, appliedAt, updatedAt, isGreaterThan);
        return jobApplications.stream().map(JobApplicationDTO::new).toList();
    }
}
