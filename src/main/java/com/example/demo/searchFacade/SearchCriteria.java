package com.example.demo.searchFacade;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class SearchCriteria {
    // Common fields for both Job and Company
    private String location;

    // Fields specific to Job
    private String jobTitle;
    private String jobDescription;
    private int minExperience;
    private int maxExperience;
    private String jobType;
    private int minSalary;
    private int maxSalary;
    private LocalDateTime jobDeadline;

    // Fields specific to Company
    private String companyName;
    private String domain;
    private String phoneNumber;
    private String email;
    private String website;
    private String companyDescription;
    private String registrationYear;
    private LocalDateTime foundationYear;
    private boolean isActive;

}
