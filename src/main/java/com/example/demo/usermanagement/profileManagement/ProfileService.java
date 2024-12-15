package com.example.demo.usermanagement.profileManagement;

import com.example.demo.jobmanagement.job.Job;
import com.example.demo.jobmanagement.job.JobDTO;
import com.example.demo.jobmanagement.job.JobRepository;
import com.example.demo.usermanagement.models.User;
import com.example.demo.usermanagement.profileManagement.skill.Skill;
import com.example.demo.usermanagement.profileManagement.skill.SkillDTO;
import com.example.demo.usermanagement.profileManagement.skill.SkillRepository;
import com.example.demo.usermanagement.profileManagement.skill.SkillRequest;
import com.example.demo.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
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

    @Autowired
    JobRepository jobRepository;

    public ProfileDTO createProfile(UUID user_id, ProfileRequest profileRequest) {

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

    public ProfileDTO updateProfile(UUID id, ProfileRequest profileRequest) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found with the id : " + id));

        // Ensure all 'ready skills' exist in the database
        Set<Skill> readySkills = new HashSet<>();
        for (SkillDTO skillDTO : profileRequest.getReadySkills()) {
            Skill skill = skillRepository.findById(skillDTO.getId())
                    .orElseThrow(() -> new RuntimeException("Skill not found with the id : " + skillDTO.getId()));
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

        // Update the profile
        profile.setName(profileRequest.getName());
        profile.setBio(profileRequest.getBio());
        profile.setEmail(profileRequest.getEmail());
        profile.setPhoneNumber(profileRequest.getPhoneNumber());
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
    }

    public List<JobDTO> getRecommendation(UUID id) {
        Set<Skill> skills = profileRepository.findSkillsByProfileId(id);
        List<Job> jobs = jobRepository.findJobsBySkills(skills);
        return jobs.stream().map(job -> new JobDTO(
                job,
                job.getCompany(), // Ensure job.getCompany() is not null
                job.getSkills().stream().map(SkillDTO::new).collect(Collectors.toSet())
        )).collect(Collectors.toList());

    }

    public Set<SkillDTO> getSkillsByProfileId(UUID id) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found with the id : " + id));
        Set<Skill> skills = profileRepository.findSkillsByProfileId(id);
        return skills.stream().map(SkillDTO::new).collect(Collectors.toSet());
    }
}
