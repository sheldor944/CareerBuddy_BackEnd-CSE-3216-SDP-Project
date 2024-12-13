package com.example.demo.jobmanagement.job;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class JobRequest {
    private UUID companyId;
    private String title;
    private String description;
    private String location;
    private int experience;
    private String jobType;
    private LocalDateTime deadline;
    private int salary;

}
