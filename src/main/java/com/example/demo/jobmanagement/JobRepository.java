package com.example.demo.jobmanagement;

import com.example.demo.jobmanagement.companymanagement.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JobRepository extends JpaRepository<Job, UUID> {

    @Query(value = "SELECT * FROM jobs j WHERE " +
            "(:title IS NULL OR j.title ILIKE CONCAT('%', :title, '%')) " +
            "AND (:description IS NULL OR j.description ILIKE CONCAT('%', :description, '%')) " ,
            nativeQuery = true)
    List<Job> findByMultipleFields(@Param("title") String title,
                                       @Param("description") String description);
}
