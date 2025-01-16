package com.example.demo.jobmanagement.jobApplication;

import com.example.demo.jobmanagement.job.Job;
import com.example.demo.jobmanagement.jobApplication.meeting.Meeting;
import com.example.demo.usermanagement.models.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "job_applications",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"job_id", "priorityIndex"}),
                @UniqueConstraint(columnNames = {"user_id", "job_id"})
        }
)
@Data
@NoArgsConstructor
public class JobApplication {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    private Job job;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String status; // Pending, Accepted, Rejected
    private LocalDateTime appliedAt;
    private LocalDateTime updatedAt;

    private double priorityIndex = 0;

    @OneToOne(mappedBy = "jobApplication", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Meeting meeting;

    public JobApplication(Job job, User user, String status, LocalDateTime appliedAt, LocalDateTime updatedAt, double priorityIndex) {
        this.job = job;
        this.user = user;
        this.status = status;
        this.appliedAt = appliedAt;
        this.updatedAt = updatedAt;
        this.priorityIndex = priorityIndex;
    }
}
