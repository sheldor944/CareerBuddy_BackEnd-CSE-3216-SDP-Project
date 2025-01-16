package com.example.demo.usermanagement.profileManagement.experience;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service

public class ExperienceService {

    ExperienceRepository experienceRepository;

    public void addExperience(ExperienceRequest experienceRequest) {
        Experience experience = new Experience(experienceRequest);
        experienceRepository.save(experience);
    }
}
