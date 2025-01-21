package com.example.demo.usermanagement.profileManagement.research;

import com.example.demo.usermanagement.profileManagement.Profile;
import com.example.demo.usermanagement.profileManagement.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class ResearchService {

    @Autowired
    ResearchRepository researchRepository;
    @Autowired
    ProfileRepository profileRepository;


    public ResearchDTO addResearch(UUID profileID , ResearchRequest researchRequest) {
        Profile profile = profileRepository.findById(profileID).
                orElseThrow(() -> new RuntimeException("Profile not found"));
        Research research = new Research(researchRequest);
        Set<Research> researches = profile.getResearches();
        researches.add(research);
        profile.setResearches(researches);
        researchRepository.save(research);
        return new ResearchDTO(research);
    }
}
