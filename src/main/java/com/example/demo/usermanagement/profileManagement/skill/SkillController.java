package com.example.demo.usermanagement.profileManagement.skill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/skill")
public class SkillController {

    @Autowired
    SkillService skillService;

    @PostMapping("/create")
    public SkillDTO createSkill(SkillRequest skillRequest){
//        System.out.println(skillRequest);
        return skillService.createSkill(skillRequest);
    }

    @GetMapping("/all")
    public List<SkillDTO> getAllSkills(){
        return skillService.getAllSkills();
    }

    @GetMapping("/skill/{skillId}")
    public SkillDTO getSkillById(@PathVariable UUID skillId){
        return skillService.getSkillById(skillId);
    }

    @DeleteMapping("/delete/{skillId}")
    public void deleteSkill(@PathVariable UUID skillId){
        skillService.deleteSkill(skillId);
    }
}
