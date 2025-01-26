package com.example.demo.jobmanagement.jobApplication;

import com.example.demo.jobmanagement.companymanagement.CompanyRepository;
import com.example.demo.jobmanagement.job.Job;
import com.example.demo.jobmanagement.job.JobRepository;
import com.example.demo.notification.EmailSender;
import com.example.demo.notification.SimpleMailMessage;
import com.example.demo.usermanagement.models.User;
import com.example.demo.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

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
    @Autowired
    EmailSender emailSender;




    public  void sendEmail(UUID userId, Job job){
        User user = userRepository.findById(userId).orElseThrow();
        String email =user.getEmail();
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage(email, "Applied For  " + job.getTitle() , " Successfully Applied at  " + job.getCompany().getName());
        System.out.println("Email sent to " + email);
        emailSender.sendEmail(simpleMailMessage);
    }


    public JobApplicationDTO applyForJob(UUID jobId, UUID userId) {
        AtomicBoolean flag = new AtomicBoolean(false);
        jobApplicationRepository.findByUserIdAndJobId(userId, jobId).ifPresent(jobApplication -> {
            System.out.println("User has already applied for this job");
            flag.set(true);
             // Exit early or perform additional logic
        });
        if(flag.get()){
            return null;
        }
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found with the id : " + jobId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with the id : " + userId));

        LocalDateTime currentDateTime = LocalDateTime.now();
        List<JobApplication> jobApplications =  jobApplicationRepository.findByJobIdOrderByPriorityIndexAsc(jobId);
        if(jobApplications.isEmpty()){
            JobApplication jobApplication = new JobApplication(job, user, "Pending", currentDateTime, currentDateTime, 1000000);    // Create a new JobApplication
            jobApplicationRepository.save(jobApplication);
            sendEmail(userId, job);

            return new JobApplicationDTO(jobApplication);
        }
        double priorityIndex = jobApplications.get(jobApplications.size() - 1).getPriorityIndex() + 1000000;


        JobApplication jobApplication = new JobApplication(job, user, "Pending", currentDateTime, currentDateTime, priorityIndex);    // Create a new JobApplication
        jobApplicationRepository.save(jobApplication);

        // send email
        sendEmail(userId, job);
        return new JobApplicationDTO(jobApplication);
    }

    public JobApplicationDTO updatePriorityIndex(UUID jobApplicationId, int position){

        System.out.println("job application id :" + jobApplicationId);
        JobApplication jobApplication = jobApplicationRepository.findById(jobApplicationId)
                .orElseThrow(() -> new RuntimeException("Job Application not found with the id : " + jobApplicationId));
        System.out.println(" got the job application ");
        List<JobApplication> jobApplications = jobApplicationRepository.findByJobIdOrderByPriorityIndexAsc(jobApplication.getJob().getId());
        System.out.println("got all the list of applications ");
        if ( position > jobApplications.size() ){
            jobApplication.setPriorityIndex(jobApplications.get(jobApplications.size() - 1).getPriorityIndex() + 1000000);
            jobApplicationRepository.save(jobApplication);
            return new JobApplicationDTO(jobApplication);
        }
        else if( position == 1){
            System.out.println(" in this condition ");
            jobApplication.setPriorityIndex(jobApplications.get(0).getPriorityIndex() / 2);
            System.out.println("no problem at all ");
            jobApplicationRepository.save(jobApplication);

            return new JobApplicationDTO(jobApplication);
        }
        else if ( position >1){
            jobApplication.setPriorityIndex((jobApplications.get(position - 2).getPriorityIndex() + jobApplications.get(position - 1).getPriorityIndex()) / 2);
            jobApplicationRepository.save(jobApplication);

            return new JobApplicationDTO(jobApplication);
        }
        System.out.println("last line ");
        return  null;
    }

    public JobApplicationDTO updateJobApplication(UUID jobApplicationId, JobApplicationRequest jobApplicationRequest) {
        JobApplication jobApplication = jobApplicationRepository.findById(jobApplicationId)
                .orElseThrow(() -> new RuntimeException("Job Application not found with the id : " + jobApplicationId));
        jobApplication.setStatus(jobApplicationRequest.getStatus());
        jobApplication.setUpdatedAt(LocalDateTime.now());
        jobApplicationRepository.save(jobApplication);
        UUID userId = jobApplication.getUser().getId();
        Job job = jobRepository.findById(jobApplication.getJob().getId()).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();
        String email =user.getEmail();
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage(email, "Your status of the application for   " + job.getTitle() + " is " + jobApplicationRequest.getStatus() , " Application status at   " + job.getCompany().getName());
        emailSender.sendEmail(simpleMailMessage);
        return new JobApplicationDTO(jobApplication);
    }

    public List<JobApplicationDTO> getAllJobApplications() {
        List<JobApplication> jobApplications = jobApplicationRepository.findAll();
        return jobApplications.stream().map(JobApplicationDTO::new).toList();
    }

    public List<JobApplicationDTO> getJobApplicationsByJobId(UUID jobId) {
        List<JobApplication> jobApplications = jobApplicationRepository.findByJobIdOrderByPriorityIndexAsc(jobId);
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
