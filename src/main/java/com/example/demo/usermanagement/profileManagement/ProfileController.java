package com.example.demo.usermanagement.profileManagement;

import com.example.demo.jobmanagement.job.JobDTO;
import com.example.demo.usermanagement.profileManagement.education.EducationRequest;
import com.example.demo.usermanagement.profileManagement.experience.ExperienceRequest;
import com.example.demo.usermanagement.profileManagement.skill.SkillDTO;
import com.example.demo.usermanagement.profileManagement.skill.SkillRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    ProfileService profileService;

    @GetMapping("/recommendation/{id}")
    public List<JobDTO> getRecommendation(@PathVariable UUID id){
        return profileService.getRecommendation(id);
    }

    @PostMapping("/{id}")
    public ProfileDTO createProfile(@PathVariable UUID id, @RequestBody ProfileRequest profileRequest){
        return profileService.createProfile(id, profileRequest);
//        return null ;
    }

    @PutMapping("/addSkill/{id}")
    public ProfileDTO addSkill(@PathVariable UUID id, @RequestBody SkillRequest skillRequest){
        return profileService.addSkillToProfile(id, skillRequest);
    }

    @DeleteMapping("/deleteExperience/{id}")
    public ProfileDTO deleteExperience(@PathVariable UUID id, @RequestBody UUID experienceId){
        return profileService.deleteExperienceFromProfile(id, experienceId);
    }

    @DeleteMapping("/deleteSkill/{id}")
    public ProfileDTO deleteSkill(@PathVariable UUID id, @RequestBody UUID skillId){
        return profileService.deleteSkillFromProfile(id, skillId);
    }

    @DeleteMapping("/deleteEducation/{id}")
    public ProfileDTO deleteEducation(@PathVariable UUID id, @RequestBody UUID educationId){
        return profileService.deleteEducationFromProfile(id, educationId);
    }
    @PutMapping("/addExperience/{id}")
    public ProfileDTO addExperience(@PathVariable UUID id, @RequestBody ExperienceRequest experienceRequest){
        return profileService.addExperienceToProfile(id, experienceRequest);
    }
    @PutMapping("/addEducation/{id}")
    public ProfileDTO addEducation(@PathVariable UUID id, @RequestBody EducationRequest educationRequest){
        return profileService.addEducationToProfile(id, educationRequest);
    }

    @GetMapping("/{id}")
    public ProfileDTO getProfileByUserID(@PathVariable UUID id){
        return profileService.getProfileByUserID(id);
    }

    @PutMapping("/{id}")
    public ProfileDTO updateProfile(@PathVariable UUID id, @RequestBody ProfileRequest profileRequest){
        return profileService.updateProfile(id, profileRequest);
    }

    @GetMapping("/skills/{id}")
    public Set<SkillDTO> getSkillsByProfileId(@PathVariable UUID id){
        return profileService.getSkillsByProfileId(id);
    }



}
