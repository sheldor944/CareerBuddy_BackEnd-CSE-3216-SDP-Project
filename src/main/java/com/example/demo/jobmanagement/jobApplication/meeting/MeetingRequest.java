package com.example.demo.jobmanagement.jobApplication.meeting;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class MeetingRequest {

    private UUID userId;
    private UUID jobApplicationId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

}
