package com.example.demo.jobmanagement;

import com.example.demo.jobmanagement.companymanagement.Company;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
public class JobRequest {
    private UUID companyId;
    private String title;
    private String description;
    private String location;
    private double experience;
    private String jobType;
    private LocalDateTime deadline;
    private double salary;

}
