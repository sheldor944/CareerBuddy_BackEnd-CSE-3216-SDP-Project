package com.example.demo.usermanagement.profileManagement.skill;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class SkillDTO {
    public UUID id;
    public String name;

    public SkillDTO(Skill skill) {
        this.id = skill.getId();
        this.name = skill.getName();
    }
}
