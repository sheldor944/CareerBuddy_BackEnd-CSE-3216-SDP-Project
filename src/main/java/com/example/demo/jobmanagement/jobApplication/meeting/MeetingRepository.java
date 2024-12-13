package com.example.demo.jobmanagement.jobApplication.meeting;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MeetingRepository extends JpaRepository<Meeting, UUID> {
    List<Meeting> findByJobApplicationId(UUID id);

    List<Meeting> findByUserId(UUID userId);
}
