package com.example.demo.jobmanagement.job;

import com.example.demo.jobmanagement.companymanagement.Company;
import com.example.demo.jobmanagement.companymanagement.CompanyRepository;
import com.example.demo.jobmanagement.jobApplication.JobApplication;
import com.example.demo.jobmanagement.jobApplication.JobApplicationDTO;
import com.example.demo.notification.EmailSender;
import com.example.demo.notification.SimpleMailMessage;
import com.example.demo.notification.UserSubscription;
import com.example.demo.notification.UserSubscriptionRepository;
import com.example.demo.searchFacade.SearchCriteria;
import com.example.demo.searchFacade.SearchFacade;
import com.example.demo.usermanagement.models.User;
import com.example.demo.usermanagement.profileManagement.skill.Skill;
import com.example.demo.usermanagement.profileManagement.skill.SkillDTO;
import com.example.demo.usermanagement.profileManagement.skill.SkillRepository;
import com.example.demo.usermanagement.repository.UserRepository;
import com.example.demo.util.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    @Autowired
    SkillRepository skillRepository;

    @Autowired
    EmailSender emailSender;

    @Autowired
    UserSubscriptionRepository userSubscriptionRepository;

    public void notifySubscriber(UUID companyID, Job job){
        List<UserSubscription> userSubscriptionList = userSubscriptionRepository.findBySubscribedCompany_Id(companyID);
        for(UserSubscription userSubscription : userSubscriptionList){
           UUID userID = userSubscription.getUser().getId();
           String email = userSubscription.getUser().getEmail();
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage(email, "New Job Posting " + job.getTitle() , "A new job has been posted by " + job.getCompany().getName());
            emailSender.sendEmail(simpleMailMessage);
        }
    }

    public JobDTO createJob(JobRequest jobRequest) {
        UUID companyID = jobRequest.getCompanyId();
        Company company = companyRepository.findById(companyID)
                .orElseThrow(() -> new RuntimeException("Company not found with the id : " + companyID));

        Set<Skill> skills = Helper.skillOrganizer(jobRequest.getNewSkills(), jobRequest.getExistingSkills(), skillRepository);

        Set<SkillDTO> skillDTOSet = skills.stream().map(SkillDTO::new).collect(Collectors.toSet());
        Job job = new JobBuilder()
                .company(company)
                .title(jobRequest.getTitle())
                .description(jobRequest.getDescription())
                .location(jobRequest.getLocation())
                .experience(jobRequest.getExperience())
                .jobType(jobRequest.getJobType())
                .deadline(jobRequest.getDeadline())
                .salary(jobRequest.getSalary())
                .skill(skills)
                .build();

        jobRepository.save(job);
        notifySubscriber(companyID, job);
//        company.postJob(job);

//        return null;
        return new JobDTO(job, company, skillDTOSet);
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
        String title = criteria.getJobTitle() != null ? criteria.getJobTitle() : null;
        String location = criteria.getLocation() != null ? criteria.getLocation() : null;
        Integer experience = criteria.getMinExperience() != null ? criteria.getMinExperience() : null;
        System.out.println("experience = " + experience);
        String jobType = criteria.getJobType() != null ? criteria.getJobType() : null;
        Integer salary = criteria.getMinSalary() != null ? criteria.getMinSalary() : null;
        LocalDateTime deadline = criteria.getJobDeadline() != null ? criteria.getJobDeadline() : null;

        System.out.println("title = " + title);
        System.out.println("location = " + location);
        System.out.println("experience = " + experience);
        System.out.println("jobType = " + jobType);
        System.out.println("salary = " + salary);
        System.out.println("deadline = " + deadline);


        return jobRepository.findByMultipleFields(title, location, experience, jobType, salary, deadline)
                .stream()
                .map(JobDTO::new)
                .collect(Collectors.toList());
    }

//    public List<JobDTO> searchJobs(SearchCriteria criteria) {
//        System.out.println("criteria in the searchJob = " + criteria);
//        return jobRepository.findByMultipleFields(
//                criteria.getJobTitle(),
//                criteria.getLocation(),
//                criteria.getMinExperience(),
//                criteria.getJobType(),
//                criteria.getMinSalary()
//        ).stream().map(JobDTO::new).collect(Collectors.toList());
//    }

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
