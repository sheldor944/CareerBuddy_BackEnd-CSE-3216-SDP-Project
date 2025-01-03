package com.example.demo.jobmanagement.job;

import com.example.demo.usermanagement.profileManagement.skill.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface JobRepository extends JpaRepository<Job, UUID> {

//    @Query("SELECT j FROM Job j " +
//            "WHERE (:title IS NULL OR j.title LIKE %:title%) " +
//            "AND (:location IS NULL OR j.location LIKE %:location%) " +
//            "AND (:experience IS NULL OR j.experience <= :experience) " +
//            "AND (:jobType IS NULL OR j.jobType LIKE %:jobType%) " +
//            "AND (:salary IS NULL OR j.salary = :salary) " )
//    List<Job> findByMultipleFields(@Param("title") String title,
//                                   @Param("location") String location,
//                                   @Param("experience") int experience,
//                                   @Param("jobType") String jobType,
//                                   @Param("salary") int salary);


//    @Query("SELECT j FROM Job j " +
//            "WHERE (:title IS NULL OR j.title LIKE %:title%) " +
//            "AND (:location IS NULL OR j.location LIKE %:location%) " +
//            "AND (:experience IS NULL OR j.experience <= :experience) " +
//            "AND (:jobType IS NULL OR j.jobType LIKE %:jobType%) " +
//            "AND (:salary IS NULL OR j.salary = :salary)")
//    List<Job> findByMultipleFields(
//            @Param("title") String title,
//            @Param("location") String location,
//            @Param("experience") Integer experience,  // Change to Integer to handle null
//            @Param("jobType") String jobType,
//            @Param("salary") Integer salary);  // Change to Integer to handle null
//

    @Query("SELECT j FROM Job j " +
            "WHERE (CAST(:title AS string) IS NULL OR j.title LIKE %:title%) " +
            "AND (CAST(:location AS string) IS NULL OR j.location LIKE %:location%) " +
            "AND (CAST(:experience AS integer) IS NULL OR j.experience <= :experience) " +
            "AND (CAST(:jobType AS string) IS NULL OR j.jobType LIKE %:jobType%) " +
            "AND (CAST(:salary AS integer) IS NULL OR j.salary = :salary) " +
            "AND (CAST(:deadline AS timestamp) IS NULL OR j.deadline <= :deadline)")
    List<Job> findByMultipleFields(
            @Param("title") String title,
            @Param("location") String location,
            @Param("experience") Integer experience,
            @Param("jobType") String jobType,
            @Param("salary") Integer salary,
            @Param("deadline") LocalDateTime deadline);

//    @Query("SELECT j FROM Job j " +
//            "WHERE (:title IS NULL OR CAST(j.title AS string) LIKE CONCAT('%', :title, '%')) " +
//            "AND (:location IS NULL OR CAST(j.location AS string) LIKE CONCAT('%', :location, '%')) " +
//            "AND (:experience IS NULL OR j.experience <= :experience) " +
//            "AND (:jobType IS NULL OR CAST(j.jobType AS string) LIKE CONCAT('%', :jobType, '%')) " +
//            "AND (:salary IS NULL OR j.salary = :salary) " +
//            "AND (:deadline IS NULL OR j.deadline <= :deadline)")
//    List<Job> findByMultipleFields(
//            @Param("title") String title,
//            @Param("location") String location,
//            @Param("experience") Integer experience,
//            @Param("jobType") String jobType,
//            @Param("salary") Integer salary,
//            @Param("deadline") LocalDateTime deadline);


    List<Job> findByCompanyId(UUID companyId);

    @Query("SELECT DISTINCT j FROM Job j JOIN j.skills s WHERE s IN :skills")
    List<Job> findJobsBySkills(@Param("skills") Set<Skill> skills);

}
