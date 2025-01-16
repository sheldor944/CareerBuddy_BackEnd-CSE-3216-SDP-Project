package com.example.demo.usermanagement.profileManagement.education;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;

@Data
@Getter
public class EducationRequest {
    private String universityName;
    private String degree;
    private String major;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;


}
