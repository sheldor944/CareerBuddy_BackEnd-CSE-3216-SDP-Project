package com.example.demo.jobmanagement.jobApplication.meeting;


import com.example.demo.jobmanagement.jobApplication.JobApplication;
import com.example.demo.usermanagement.models.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "meetings")
@Data
@NoArgsConstructor
public class Meeting {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_application_id", nullable = false)
    private JobApplication jobApplication;

    public Meeting(MeetingRequest meetingRequest, User user, JobApplication jobApplication) {
        this.startTime = meetingRequest.getStartTime();
        this.endTime = meetingRequest.getEndTime();
        this.user = user;
        this.jobApplication = jobApplication;
    }

}
