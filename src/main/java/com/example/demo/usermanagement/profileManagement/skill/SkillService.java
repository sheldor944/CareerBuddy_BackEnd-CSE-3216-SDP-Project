package com.example.demo.usermanagement.profileManagement.skill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SkillService {

    @Autowired
    SkillRepository skillRepository;
    public SkillDTO createSkill(SkillRequest skillRequest){
        Skill skill = new Skill(skillRequest);
        skillRepository.save(skill);
        return new SkillDTO(skill);
    }


    public List<SkillDTO> getAllSkills() {
        List<Skill> skills = skillRepository.findAll();
        return skills.stream().map(SkillDTO::new).collect(Collectors.toList());
    }


    public SkillDTO getSkillById(UUID skillId) {
        Skill skill = skillRepository.findById(skillId).orElseThrow(() -> new RuntimeException("Skill not found"));
        return new SkillDTO(skill);
    }

    public void deleteSkill(UUID skillId) {
        skillRepository.deleteById(skillId);
    }
}
