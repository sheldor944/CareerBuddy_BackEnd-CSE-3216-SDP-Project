package com.example.demo.notification;

import java.util.UUID;

public abstract class BaseNotification implements Notification {
    private UUID senderId;
    private String recipientId;
    private String message;
    private NotificationType type;

    public BaseNotification(UUID senderId, String recipientId, String message, NotificationType type) {
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.message = message;
        this.type = type;
    }

    // Getters
    @Override
    public UUID getSenderId() {
        return senderId;
    }

    @Override
    public String getRecipientId() {
        return recipientId;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public NotificationType getType() {
        return type;
    }
}