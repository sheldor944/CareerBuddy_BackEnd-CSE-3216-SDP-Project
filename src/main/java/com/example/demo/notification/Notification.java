package com.example.demo.notification;

import java.util.UUID;

public interface Notification {
    UUID getSenderId();
    String getRecipientId();
    String getMessage();
    NotificationType getType();
    SimpleMailMessage getSimpleMailMessage();
}
