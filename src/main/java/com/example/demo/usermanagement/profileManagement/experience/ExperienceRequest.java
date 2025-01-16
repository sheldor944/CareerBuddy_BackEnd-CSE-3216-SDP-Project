package com.example.demo.usermanagement.profileManagement.experience;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;

@Data
@Getter
public class ExperienceRequest {

        private String title;
        private String companyName;
        private String city;
        private String state;
        private LocalDate startDate;
        private LocalDate endDate;
        private boolean currentlyWorking;
        private String workSummary;
}
