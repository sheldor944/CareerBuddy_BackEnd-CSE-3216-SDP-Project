package com.example.demo.usermanagement.profileManagement;

import com.example.demo.usermanagement.models.User;
import com.example.demo.usermanagement.profileManagement.skill.Skill;
import com.example.demo.usermanagement.profileManagement.skill.SkillDTO;
import com.example.demo.usermanagement.profileManagement.skill.SkillRepository;
import com.example.demo.usermanagement.profileManagement.skill.SkillRequest;
import com.example.demo.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProfileService {
    @Autowired
    ProfileRepository profileRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    SkillRepository skillRepository;

    public ProfileDTO createProfile(ProfileRequest profileRequest) {
        UUID user_id = profileRequest.getUser_id();
        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new RuntimeException("Company not found with the id : " + user_id));

        // Ensure all 'ready skills' exist in the database
        Set<Skill> readySkills = new HashSet<>();
        for (SkillDTO skillDTO : profileRequest.getReadySkills()) {
            Skill skill = skillRepository.findById(skillDTO.getId())
                    .orElseThrow(() -> new RuntimeException("Skill not found with the id : " + skillDTO.getId()));
//            readySkills.add(skill);
            readySkills.add(skill);
        }

        // Add only new skills to the database
        Set<Skill> newSkills = new HashSet<>();
        for (SkillRequest skillRequest : profileRequest.getNewSkills()) {
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

        // Create and save the profile
        Profile profile = new Profile(profileRequest);
        profile.setUser(user);
        profile.setSkills(allSkills);
        profile = profileRepository.save(profile);

        // Map to DTO and return
        ProfileDTO profileDTO = new ProfileDTO(
                profile.getId(),
                profile.getName(),
                profile.getBio(),
                profile.getEmail(),
                profile.getPhoneNumber(),
                profile.getSkills().stream().map(SkillDTO::new).collect(Collectors.toSet())
        );
        return profileDTO;
//        return null;
    }

//
//
//
    public ProfileDTO getProfileByUserID(UUID id){
        Profile profile = profileRepository.findByUserId(id);
        return new ProfileDTO(
                profile.getId(),
                profile.getName(),
                profile.getBio(),
                profile.getEmail(),
                profile.getPhoneNumber(),
                profile.getSkills().stream().map(SkillDTO::new).collect(Collectors.toSet())
        ) ;
    }
}
