package com.example.demo.usermanagement.profileManagement.resume;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ResumeRepositoryFactory {

    @Autowired
    ResumeRepository resumeRepository;
    @Autowired
    ProfessionalResumeRepository professionalResumeRepository;

    public JpaRepository<? extends Object, UUID> createRepository(String type) {
        if (type.equals("student")) {
            return resumeRepository;
        } else if (type.equals("professional")) {
            return professionalResumeRepository;
        }
        return null;
    }
}
