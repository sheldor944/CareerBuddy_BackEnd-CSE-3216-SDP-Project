package com.example.demo.jobmanagement.companymanagement;

import com.example.demo.jobmanagement.job.Job;
import com.example.demo.notification.*;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "companies")
@Data
@NoArgsConstructor
public class Company implements NotificationSubject {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID", updatable = false, nullable = false)
    private UUID id;
    private String name ;

    private String location;
    private String phoneNumber;
    private String email;
    private String website;

    private String domain;
    private String description;
    @Enumerated(EnumType.STRING) // Store enum as a string in the database
    private CompanySize size;

    private LocalDateTime foundationYear;
    private String registrationYear;
    private boolean isActive;


    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Job> jobs;


    @Transient
    private List<NotificationObserver> subscribers = new ArrayList<>();

    // Notification strategies
    @Transient
    private List<NotificationStrategy> notificationStrategies = new ArrayList<>();

    // Implement NotificationSubject methods
    @Override
    public void addSubscriber(NotificationObserver observer) {
        subscribers.add(observer);
    }

    @Override
    public void removeSubscriber(NotificationObserver observer) {
        subscribers.remove(observer);
    }

    @Override
    public void notifySubscribers(Notification notification) {
        for (NotificationObserver observer : subscribers) {
            observer.update(notification);
        }
    }

    // Method to post a job and notify subscribers
    public void postJob(Job job) {
        // Save job logic would be in service layer

        // Create notification
        Notification jobNotification = new JobPostingNotification(
                this.getId(),
                null, // recipient will be set by specific observers
                "New job posted: " + job.getTitle(),
                NotificationType.EMAIL,
                job.getId()
        );

        // Notify all subscribers
        notifySubscribers(jobNotification);
    }

    // Add notification strategy
    public void addNotificationStrategy(NotificationStrategy strategy) {
        notificationStrategies.add(strategy);
    }

    // Remove notification strategy
    public void removeNotificationStrategy(NotificationStrategy strategy) {
        notificationStrategies.remove(strategy);
    }


    public Company(CompanyRequest companyRequest){
        this.name = companyRequest.getCompanyName();
        this.domain = companyRequest.getDomain();
        this.location = companyRequest.getLocation();
    }

    public Company(String name, String location, String phoneNumber, String email, String website, String registrationYear, List<Job> jobs, boolean isActive, LocalDateTime foundationYear, CompanySize size, String domain, String description) {
        this.name = name;
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.website = website;
        this.registrationYear = registrationYear;
        this.jobs = jobs;
        this.isActive = isActive;
        this.foundationYear = foundationYear;
        this.size = size;
        this.domain = domain;
        this.description = description;
    }
}
