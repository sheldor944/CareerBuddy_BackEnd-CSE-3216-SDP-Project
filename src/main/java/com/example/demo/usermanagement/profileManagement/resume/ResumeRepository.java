package com.example.demo.usermanagement.profileManagement.resume;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ResumeRepository extends JpaRepository<Resume, UUID> {
    List<Resume> findByProfile_Id(UUID profileId);
}
