package com.example.demo.notification;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserSubscriptionRepository extends JpaRepository<UserSubscription, UUID> {

}
