package com.example.demo.usermanagement.profileManagement;

import com.example.demo.usermanagement.models.User;
import com.example.demo.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileService {
    @Autowired
    ProfileRepository profileRepository;
    @Autowired
    UserRepository userRepository;

    public ProfileDTO createProfile(ProfileRequest profileRequest)
    {
        UUID user_id = profileRequest.getUser_id();
        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new RuntimeException("Company not found with the id : " + user_id ));
        Profile profile = new Profile(profileRequest);
        profile.setUser(user);
        profile = profileRepository.save(profile);
        ProfileDTO profileDTO = new ProfileDTO(
                profile.getId(),
                profile.getName(),
                profile.getBio(),
                profile.getEmail(),
                profile.getPhoneNumber()
        );
        return profileDTO;
    }

    public ProfileDTO getProfileByUserID(UUID id){
        Profile profile = profileRepository.findByUserId(id);
        return new ProfileDTO(
                profile.getId(),
                profile.getName(),
                profile.getBio(),
                profile.getEmail(),
                profile.getPhoneNumber()
        ) ;
    }
}
