package com.example.demo.notification;

import com.example.demo.jobmanagement.companymanagement.Company;
import com.example.demo.notification.Notification;
import com.example.demo.notification.NotificationObserver;
import com.example.demo.notification.NotificationStrategy;
import com.example.demo.usermanagement.models.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "user_subscriptions", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "company_id"})
})
@Data
public class UserSubscription implements NotificationObserver {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company subscribedCompany;

    // List of notification strategies for this subscription
    @Transient
    private List<NotificationStrategy> notificationStrategies = new ArrayList<>();

    @Override
    public void update(Notification notification) {
        // Send notification using all registered strategies
        for (NotificationStrategy strategy : notificationStrategies) {
            // Customize the notification for the specific user
            Notification personalizedNotification = personalizeNotification(notification);
            strategy.send(personalizedNotification);
        }
    }

    // Personalize notification for the specific user
    private Notification personalizeNotification(Notification originalNotification) {
        return new JobPostingNotification(
                originalNotification.getSenderId(),
                user.getEmail(), // Set recipient email
                originalNotification.getMessage(),
                originalNotification.getType(),
                ((JobPostingNotification)originalNotification).getJobId(),
                new SimpleMailMessage()
        );
    }

    // Add a notification strategy
    public void addNotificationStrategy(NotificationStrategy strategy) {
        notificationStrategies.add(strategy);
    }
}
