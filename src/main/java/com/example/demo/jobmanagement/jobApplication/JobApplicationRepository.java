package com.example.demo.jobmanagement.jobApplication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JobApplicationRepository extends JpaRepository<JobApplication, UUID> {
    List<JobApplication> findByJobId(UUID jobId);
    List<JobApplication> findByJobIdOrderByPriorityIndexAsc(UUID jobId);

    List<JobApplication> findByUserId(UUID userId);

    @Query("SELECT ja FROM JobApplication ja WHERE " +
            "(:status IS NULL OR ja.status = :status) AND " +
            "(:appliedAt IS NULL OR " +
            "(:isGreaterThan = true AND ja.appliedAt >= :appliedAt) OR " +
            "(:isGreaterThan = false AND ja.appliedAt <= :appliedAt) OR " +
            "(:isGreaterThan IS NULL AND ja.appliedAt = :appliedAt)) AND " +
            "(:updatedAt IS NULL OR " +
            "(:isGreaterThan = true AND ja.updatedAt >= :updatedAt) OR " +
            "(:isGreaterThan = false AND ja.updatedAt <= :updatedAt) OR " +
            "(:isGreaterThan IS NULL AND ja.updatedAt = :updatedAt))")
    List<JobApplication> findByFilter(
            @Param("status") String status,
            @Param("appliedAt") LocalDateTime appliedAt,
            @Param("updatedAt") LocalDateTime updatedAt,
            @Param("isGreaterThan") Boolean isGreaterThan // Use Boolean to allow null
    );

    Optional<Object> findByUserIdAndJobId(UUID userId, UUID jobId);
}
