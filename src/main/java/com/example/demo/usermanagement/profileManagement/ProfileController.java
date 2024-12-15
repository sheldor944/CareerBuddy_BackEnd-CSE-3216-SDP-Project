package com.example.demo.usermanagement.profileManagement;

import com.example.demo.jobmanagement.job.JobDTO;
import com.example.demo.usermanagement.profileManagement.skill.SkillDTO;
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
