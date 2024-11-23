package com.example.demo.usermanagement.profileManagement;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @PostMapping
    public ProfileDTO createProfile(@RequestBody ProfileRequest profileRequest){
        return profileService.createProfile(profileRequest);
//        return null ;
    }
}
