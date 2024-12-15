package com.example.demo.jobmanagement.job;

import com.example.demo.usermanagement.profileManagement.skill.Skill;
import com.example.demo.usermanagement.profileManagement.skill.SkillDTO;
import com.example.demo.usermanagement.profileManagement.skill.SkillRequest;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
public class JobRequest {
    private UUID companyId;
    private String title;
    private String description;
    private String location;
    private int experience;
    private String jobType;
    private LocalDateTime deadline;
    private int salary;

    private Set<SkillDTO> existingSkills;

    private Set<SkillRequest> newSkills;

}
