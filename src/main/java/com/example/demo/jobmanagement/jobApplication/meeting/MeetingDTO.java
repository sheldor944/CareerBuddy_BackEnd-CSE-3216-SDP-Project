package com.example.demo.jobmanagement.jobApplication.meeting;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class MeetingDTO {
    private UUID id;
    private UUID userId;
    private UUID jobApplicationId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public MeetingDTO(Meeting meeting) {
        this.id = meeting.getId();
        this.userId = meeting.getUser().getId();
        this.jobApplicationId = meeting.getJobApplication().getId();
        this.startTime = meeting.getStartTime();
        this.endTime = meeting.getEndTime();
    }

}
