package com.example.demo.usermanagement.profileManagement.skill;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SkillRepository extends JpaRepository<Skill, UUID> {
    Skill findByName(String name);
}
