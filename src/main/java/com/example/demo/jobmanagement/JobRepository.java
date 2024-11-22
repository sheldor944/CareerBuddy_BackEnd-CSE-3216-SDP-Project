package com.example.demo.jobmanagement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface JobRepository extends JpaRepository<Job, UUID> {

    @Query("SELECT j FROM Job j " +
            "WHERE (:title IS NULL OR j.title LIKE %:title%) " +
            "AND (:location IS NULL OR j.location LIKE %:location%) " +
            "AND (:experience IS NULL OR j.experience <= :experience) " +
            "AND (:jobType IS NULL OR j.jobType LIKE %:jobType%) " +
            "AND (:salary IS NULL OR j.salary = :salary) " )
    List<Job> findByMultipleFields(@Param("title") String title,
                                   @Param("location") String location,
                                   @Param("experience") Double experience,
                                   @Param("jobType") String jobType,
                                   @Param("salary") Double salary);




}
