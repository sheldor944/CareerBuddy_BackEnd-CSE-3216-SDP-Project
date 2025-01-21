package com.example.demo;

import com.example.demo.jobmanagement.job.JobRepository;
import com.example.demo.jobmanagement.companymanagement.CompanyRepository;
import com.example.demo.jobmanagement.job.SavedJobsRepository;
import com.example.demo.jobmanagement.jobApplication.JobApplicationRepository;
import com.example.demo.notification.UserSubscriptionRepository;
import com.example.demo.rolemanagement.RoleRepository;
import com.example.demo.usermanagement.profileManagement.ProfileRepository;
import com.example.demo.usermanagement.profileManagement.education.EducationRepository;
import com.example.demo.usermanagement.profileManagement.experience.ExperienceRepository;
import com.example.demo.usermanagement.profileManagement.resume.ResumeRepository;
import com.example.demo.usermanagement.profileManagement.skill.SkillRepository;
import com.example.demo.usermanagement.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = {
		UserRepository.class,
		RoleRepository.class,
		CompanyRepository.class,
		JobRepository.class,
		ProfileRepository.class,
		JobApplicationRepository.class,
		SkillRepository.class,
		UserSubscriptionRepository.class,
		ResumeRepository.class,
		EducationRepository.class,
		ExperienceRepository.class,
		SavedJobsRepository.class

})
@RestController
public class JobPortalApplication {

	public static void main(String[] args) {

		SpringApplication.run(JobPortalApplication.class, args);
	}

	@GetMapping
	public String f(){
		return  "Hello";
	}
}
