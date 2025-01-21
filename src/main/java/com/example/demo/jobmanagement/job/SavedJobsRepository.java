package com.example.demo.jobmanagement.job;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SavedJobsRepository extends JpaRepository<SavedJobs, UUID> {
    List<SavedJobs> findByProfileId(UUID profileId);
    List<SavedJobs> findByJobId(UUID jobId);
    void deleteByProfileIdAndJobId(UUID profileId, UUID jobId);
    Optional<SavedJobs> findByProfileIdAndJobId(UUID profileId, UUID jobId);
}
