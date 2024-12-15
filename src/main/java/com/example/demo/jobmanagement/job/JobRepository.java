package com.example.demo.jobmanagement.job;

import com.example.demo.usermanagement.profileManagement.skill.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;
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
                                   @Param("experience") int experience,
                                   @Param("jobType") String jobType,
                                   @Param("salary") int salary);


    List<Job> findByCompanyId(UUID companyId);

    @Query("SELECT DISTINCT j FROM Job j JOIN j.skills s WHERE s IN :skills")
    List<Job> findJobsBySkills(@Param("skills") Set<Skill> skills);

}
