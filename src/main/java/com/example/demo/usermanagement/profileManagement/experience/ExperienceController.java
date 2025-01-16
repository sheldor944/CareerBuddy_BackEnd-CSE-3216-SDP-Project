package com.example.demo.usermanagement.profileManagement.experience;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/experience")
public class ExperienceController {

    @Autowired
    ExperienceService experienceService;

    @PostMapping("/add")
    public void addExperience(ExperienceRequest experienceRequest) {
        experienceService.addExperience(experienceRequest);
    }
}
