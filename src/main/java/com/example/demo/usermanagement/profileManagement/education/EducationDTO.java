package com.example.demo.usermanagement.profileManagement.education;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
public class EducationDTO {
    private UUID id;
    private String universityName;
    private String degree;
    private String major;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;

    public EducationDTO(Education education) {
        this.id = education.getId();
        this.universityName = education.getUniversityName();
        this.degree = education.getDegree();
        this.major = education.getMajor();
        this.description = education.getDescription();
        this.startDate = education.getStartDate();
        this.endDate = education.getEndDate();
    }
}
