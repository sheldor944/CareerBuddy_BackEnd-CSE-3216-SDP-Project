package com.example.demo.usermanagement.profileManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/profile")
public class ProfileConroller {
    @Autowired
    ProfileService profileService;

    @PostMapping
    public ProfileDTO createProfile(@RequestBody ProfileRequest profileRequest){
        return profileService.createProfile(profileRequest);
//        return null ;
    }

    @GetMapping("/{id}")
    public ProfileDTO getProbileByUserID(@PathVariable UUID id){
        return profileService.getProfileByUserID(id);
    }
}
