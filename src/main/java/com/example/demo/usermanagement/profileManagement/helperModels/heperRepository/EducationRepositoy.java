package com.example.demo.usermanagement.profileManagement.helperModels.heperRepository;

import com.example.demo.usermanagement.profileManagement.helperModels.Education;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EducationRepositoy extends JpaRepository<Education, UUID> {
}
