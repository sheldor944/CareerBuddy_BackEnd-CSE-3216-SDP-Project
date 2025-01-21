package com.example.demo.jobmanagement.job;

import lombok.Data;

import java.util.UUID;

@Data
public class SavedJobsRequest {
    private UUID jobId;
    private UUID profileId;
}
