package com.example.demo.usermanagement.profileManagement.resume;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProfessionalResumeRepository  extends JpaRepository<ProfessionalResume, UUID> {
    List<Resume> findByProfile_Id(UUID profileId);
}
