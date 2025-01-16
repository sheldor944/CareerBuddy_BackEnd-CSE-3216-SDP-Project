package com.example.demo.jobmanagement.jobApplication;

import lombok.Data;

import java.util.UUID;

@Data
public class JobApplicationDTO {
    private UUID id;
    private UUID jobId;
    private UUID userId;
    private String status;
    private String appliedAt;
    private String updatedAt;
    private double PriorityIndex;

    public JobApplicationDTO(JobApplication jobApplication) {
        this.id = jobApplication.getId();
        this.jobId = jobApplication.getJob().getId();
        this.userId = jobApplication.getUser().getId();
        this.status = jobApplication.getStatus();
        this.appliedAt = jobApplication.getAppliedAt().toString();
        this.updatedAt = jobApplication.getUpdatedAt().toString();
        this.PriorityIndex = jobApplication.getPriorityIndex();
    }
    public JobApplicationDTO(String status, String appliedAt, String updatedAt) {
        this.status = status;
        this.appliedAt = appliedAt;
        this.updatedAt = updatedAt;
    }


}
