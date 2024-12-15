package com.example.demo.usermanagement.profileManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    ProfileService profileService;

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
}
