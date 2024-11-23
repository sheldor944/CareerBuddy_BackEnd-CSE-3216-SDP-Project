package com.example.demo.usermanagement.profileManagement;


import com.example.demo.usermanagement.models.User;
import com.example.demo.usermanagement.profileManagement.helperModels.Address;
import com.example.demo.usermanagement.profileManagement.helperModels.Education;
import com.example.demo.usermanagement.profileManagement.helperModels.heperRepository.AddressRepository;
import com.example.demo.usermanagement.profileManagement.helperModels.heperRepository.EducationRepositoy;
import com.example.demo.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProfileService {
    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository ;

    @Autowired
    EducationRepositoy educationRepositoy;
    public ProfileDTO createProfile(ProfileRequest profileRequest)
    {
        UUID user_id = profileRequest.getUser_id();
        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new RuntimeException("Company not found with the id : " + user_id ));

        Profile profile = new Profile(profileRequest  ,user );

        ProfileRequest.AddressRequest providedAddress = profileRequest.getAddress();
        Address address = addressRepository.save(new Address(providedAddress));
        profile.setAddress(address);
        List<ProfileRequest.EducationRequest> educationRequests = profileRequest.getEducation();
        List<Education> educations = new ArrayList<>();

// Assuming profile is the Profile object you're saving
        for (ProfileRequest.EducationRequest educationRequest : educationRequests) {
            Education education = new Education(educationRequest);
            education.setProfile(profile); // Set the profile to the education
            educations.add(education);
        }

        educationRepositoy.saveAll(educations);


        System.out.println(profileRepository.save(profile));
        return new ProfileDTO(profile) ;
    }
}
