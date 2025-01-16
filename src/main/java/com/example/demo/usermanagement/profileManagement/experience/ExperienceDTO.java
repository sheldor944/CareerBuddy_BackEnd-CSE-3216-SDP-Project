package com.example.demo.usermanagement.profileManagement.experience;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
public class ExperienceDTO {
    UUID id;
    private String title;
    private String companyName;
    private String city;
    private String state;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean currentlyWorking;
    private String workSummary;

    public ExperienceDTO(String title, String companyName, String city, String state, LocalDate startDate, LocalDate endDate, boolean currentlyWorking, String workSummary) {
        this.title = title;
        this.companyName = companyName;
        this.city = city;
        this.state = state;
        this.startDate = startDate;
        this.endDate = endDate;
        this.currentlyWorking = currentlyWorking;
        this.workSummary = workSummary;
    }
    public ExperienceDTO(Experience experience) {
        this.id = experience.getId();
        this.title = experience.getTitle();
        this.companyName = experience.getCompanyName();
        this.city = experience.getCity();
        this.state = experience.getState();
        this.startDate = experience.getStartDate();
        this.endDate = experience.getEndDate();
        this.currentlyWorking = experience.isCurrentlyWorking();
        this.workSummary = experience.getWorkSummary();
    }
}
