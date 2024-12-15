package com.example.demo.util;

import com.example.demo.usermanagement.profileManagement.skill.Skill;
import com.example.demo.usermanagement.profileManagement.skill.SkillDTO;
import com.example.demo.usermanagement.profileManagement.skill.SkillRepository;
import com.example.demo.usermanagement.profileManagement.skill.SkillRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class Helper {


    public static Set<Skill> skillOrganizer(Set<SkillRequest> skillRequests, Set<SkillDTO> skillDTOSet, SkillRepository skillRepository){
        // Ensure all 'ready skills' exist in the database
        Set<Skill> readySkills = new HashSet<>();
        if(skillDTOSet != null){
            for (SkillDTO skillDTO : skillDTOSet) {
                Skill skill = skillRepository.findById(skillDTO.getId())
                        .orElseThrow(() -> new RuntimeException("Skill not found with the id : " + skillDTO.getId()));
//            readySkills.add(skill);
                readySkills.add(skill);
            }
        }


        // Add only new skills to the database
        Set<Skill> newSkills = new HashSet<>();
        if(skillRequests == null){
            return readySkills;
        }
        for (SkillRequest skillRequest : skillRequests) {
            Skill existingSkill = skillRepository.findByName(skillRequest.getName());
            if (existingSkill == null) {

                Skill newSkill = new Skill(skillRequest);
                skillRepository.save(newSkill); // Save the new skill
                newSkills.add(newSkill); // Add to the set of new skills
            } else {

                newSkills.add(existingSkill); // Add existing skill to the set
            }
        }

        // Combine ready skills (existing) and new skills
        Set<Skill> allSkills = new HashSet<>(readySkills);
        allSkills.addAll(newSkills);

        return  allSkills;

    }
}
