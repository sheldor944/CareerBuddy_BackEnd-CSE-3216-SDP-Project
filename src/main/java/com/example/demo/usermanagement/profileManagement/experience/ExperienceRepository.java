package com.example.demo.usermanagement.profileManagement.experience;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExperienceRepository  extends JpaRepository<Experience, UUID> {

}
