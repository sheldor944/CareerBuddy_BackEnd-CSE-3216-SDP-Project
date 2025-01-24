package com.example.demo.notification;

import com.example.demo.jobmanagement.companymanagement.CompanySize;
import com.example.demo.jobmanagement.job.Job;
import com.example.demo.usermanagement.models.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class CompanySubject implements NotificationSubject {
    private List<NotificationObserver> observers = new ArrayList<>();

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

    @Override
    public void addSubscriber(NotificationObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeSubscriber(NotificationObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifySubscribers(Notification notification) {
        for (NotificationObserver observer : observers) {
            observer.update(notification);
        }
    }
    public CompanySubject(User user, String name, String location, String phoneNumber, String email, String website, String registrationYear, List<Job> jobs, boolean isActive, LocalDateTime foundationYear, CompanySize size, String domain, String description) {

        this.name = name;
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.website = website;
        this.registrationYear = registrationYear;

        this.isActive = isActive;
        this.foundationYear = foundationYear;
        this.size = size;
        this.domain = domain;
        this.description = description;
    }
}
