package com.example.demo.notification;

import java.util.UUID;

public class JobPostingNotification extends BaseNotification {
    private UUID jobId;

    public JobPostingNotification(
            UUID senderId,
            String recipientId,
            String message,
            NotificationType type,
            UUID jobId,
            SimpleMailMessage simpleMailMessage
    ) {
        super(senderId, recipientId, message, type, simpleMailMessage);
        this.jobId = jobId;
    }

    public UUID getJobId() {
        return jobId;
    }
}
