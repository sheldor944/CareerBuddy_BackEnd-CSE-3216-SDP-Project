package com.example.demo.jobmanagement;

import lombok.Data;

import java.util.UUID;

@Data
public class JobRequest {
    private UUID companyId;
    private String title;
    private String description;

}
