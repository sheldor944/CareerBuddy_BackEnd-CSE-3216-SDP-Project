package com.example.demo;

import com.example.demo.jobmanagement.JobRepository;
import com.example.demo.jobmanagement.companymanagement.CompanyRepository;
import com.example.demo.rolemanagement.RoleRepository;
import com.example.demo.usermanagement.profileManagement.ProfileRepository;
import com.example.demo.usermanagement.profileManagement.helperModels.heperRepository.AddressRepository;
import com.example.demo.usermanagement.profileManagement.helperModels.heperRepository.EducationRepositoy;
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
		AddressRepository.class,
		EducationRepositoy.class

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
