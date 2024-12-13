package com.example.demo.jobmanagement.jobApplication;

import com.example.demo.jobmanagement.job.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/jobApplication")
public class JobApplicationController {

    @Autowired
    JobApplicationService jobApplicationService;

    @GetMapping("/all")
    public List<JobApplicationDTO> getAllJobApplications(){
        return jobApplicationService.getAllJobApplications();
    }

    @GetMapping("/job/{jobId}")
    public List<JobApplicationDTO> getJobApplicationsByJobId(@PathVariable UUID jobId){
        return jobApplicationService.getJobApplicationsByJobId(jobId);
    }

    @GetMapping("/company/{companyId}")
    public List<JobApplicationDTO> getJobApplicationsByCompanyId(@PathVariable UUID companyId){
        return jobApplicationService.getJobApplicationsByCompanyId(companyId);
    }

    @GetMapping("/applicationID/{jobApplicationId}")
    public JobApplicationDTO getJobApplicationById(@PathVariable UUID jobApplicationId){
        return jobApplicationService.getJobApplicationById(jobApplicationId);
    }

    @GetMapping("/user/{userId}")
    public List<JobApplicationDTO> getJobApplicationsByUserId(@PathVariable UUID userId){
        return jobApplicationService.getJobApplicationsByUserId(userId);
    }

    @PostMapping("/apply/{jobId}")
    public JobApplicationDTO applyForJob(@PathVariable UUID jobId, UUID userId){
        return jobApplicationService.applyForJob(jobId, userId);
    }

    @GetMapping("/filter")
    public List<JobApplicationDTO> getJobApplicationsByFilter(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) LocalDateTime appliedAt,
            @RequestParam(required = false) LocalDateTime updatedAt,
            @RequestParam(required = false) boolean isGreaterThan
    ){
        return jobApplicationService.getJobApplicationsByFilter(status, appliedAt, updatedAt, isGreaterThan);
    }





    @PutMapping("/update/{jobApplicationId}")
    public JobApplicationDTO updateJobApplication(@PathVariable UUID jobApplicationId, @RequestBody JobApplicationRequest jobApplicationRequest){
        return jobApplicationService.updateJobApplication(jobApplicationId, jobApplicationRequest);
    }
}
