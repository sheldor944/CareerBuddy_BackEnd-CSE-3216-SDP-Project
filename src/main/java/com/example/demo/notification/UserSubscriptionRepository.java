package com.example.demo.notification;

import com.example.demo.jobmanagement.companymanagement.Company;
import com.example.demo.usermanagement.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserSubscriptionRepository extends JpaRepository<UserSubscription, UUID> {

    Optional<UserSubscription> findByUserAndSubscribedCompany(User user, Company company);

    List<UserSubscription> findBySubscribedCompany_Id(UUID companyID);
}
