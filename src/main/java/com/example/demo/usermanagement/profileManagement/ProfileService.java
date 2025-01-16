package com.example.demo.usermanagement.profileManagement;

import com.example.demo.jobmanagement.job.Job;
import com.example.demo.jobmanagement.job.JobDTO;
import com.example.demo.jobmanagement.job.JobRepository;
import com.example.demo.usermanagement.models.User;
import com.example.demo.usermanagement.profileManagement.education.Education;
import com.example.demo.usermanagement.profileManagement.education.EducationRepository;
import com.example.demo.usermanagement.profileManagement.education.EducationRequest;
import com.example.demo.usermanagement.profileManagement.experience.Experience;
import com.example.demo.usermanagement.profileManagement.experience.ExperienceRepository;
import com.example.demo.usermanagement.profileManagement.experience.ExperienceRequest;
import com.example.demo.usermanagement.profileManagement.skill.Skill;
import com.example.demo.usermanagement.profileManagement.skill.SkillDTO;
import com.example.demo.usermanagement.profileManagement.experience.ExperienceDTO;
import com.example.demo.usermanagement.profileManagement.education.EducationDTO;
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

    @Autowired
    ExperienceRepository experienceRepository;

    @Autowired
    EducationRepository educationRepository;

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
        Set<Experience> experiences = new HashSet<>();
        // adding the experiences to the profile
        for( ExperienceRequest experienceRequest : profileRequest.getExperiences()){
            Experience experience = new Experience(experienceRequest);
            experiences.add(experience);
            experienceRepository.save(experience);
        }
        // adding education
        Set<Education> educations = new HashSet<>();
        for(EducationRequest educationRequest : profileRequest.getEducations()){
            Education education = new Education(educationRequest);
            educations.add(education);
            educationRepository.save(education);
        }

        // Combine ready skills (existing) and new skills
        Set<Skill> allSkills = new HashSet<>(readySkills);
        allSkills.addAll(newSkills);

        // Create and save the profile
        Profile profile = new Profile(profileRequest);
        profile.setUser(user);
        profile.setSkills(allSkills);
        profile.setExperiences(experiences);
        profile.setEducations(educations);
        profile = profileRepository.save(profile);

        // Map to DTO and return
        ProfileDTO profileDTO = new ProfileDTO(
                profile.getId(),
                profile.getName(),
                profile.getBio(),
                profile.getEmail(),
                profile.getPhoneNumber(),
                profile.getSkills().stream().map(SkillDTO::new).collect(Collectors.toSet()),
                profile.getAddress(),
                profile.getExperiences().stream().map(ExperienceDTO::new).collect(Collectors.toSet()),
                profile.getEducations().stream().map(EducationDTO::new).collect(Collectors.toSet())
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
                profile.getSkills().stream().map(SkillDTO::new).collect(Collectors.toSet()),
                profile.getAddress(),
                profile.getExperiences().stream().map(ExperienceDTO::new).collect(Collectors.toSet()),
                profile.getEducations().stream().map(EducationDTO::new).collect(Collectors.toSet())
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

        Set<Experience> experiences = new HashSet<>();
        // adding the experiences to the profile
        for( ExperienceRequest experienceRequest : profileRequest.getExperiences()){
            Experience experience = new Experience(experienceRequest);
            experiences.add(experience);
            experienceRepository.save(experience);
        }
        Set<Education> educations = new HashSet<>();
        for(EducationRequest educationRequest : profileRequest.getEducations()){
            Education education = new Education(educationRequest);
            educations.add(education);
            educationRepository.save(education);
        }
        profile.setEducations(educations);
        // Update the profile
        profile.setExperiences(experiences);
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
                profile.getSkills().stream().map(SkillDTO::new).collect(Collectors.toSet()),
                profile.getAddress(),
                profile.getExperiences().stream().map(ExperienceDTO::new).collect(Collectors.toSet()),
                profile.getEducations().stream().map(EducationDTO::new).collect(Collectors.toSet())
        );
        return profileDTO;
    }
    public List<JobDTO> sortJobsBySkillMatch(Set<Skill> profileSkills, List<Job> jobs) {
        return jobs.stream()
                .sorted((job1, job2) -> {
                    // Calculate match counts for both jobs
                    long matchesForJob1 = job1.getSkills().stream().filter(profileSkills::contains).count();
                    long matchesForJob2 = job2.getSkills().stream().filter(profileSkills::contains).count();

                    // Check if they are complete matches
                    boolean isCompleteMatch1 = job1.getSkills().containsAll(profileSkills);
                    boolean isCompleteMatch2 = job2.getSkills().containsAll(profileSkills);

                    // Prioritize complete matches first, then sort by match count
                    if (isCompleteMatch1 && !isCompleteMatch2) {
                        return -1; // job1 is a complete match, comes first
                    } else if (!isCompleteMatch1 && isCompleteMatch2) {
                        return 1; // job2 is a complete match, comes first
                    }

                    // If both are complete or neither, sort by match count
                    return Long.compare(matchesForJob2, matchesForJob1);
                })
                .map(job -> new JobDTO(
                        job,
                        job.getCompany(),
                        job.getSkills().stream().map(SkillDTO::new).collect(Collectors.toSet())
                ))
                .collect(Collectors.toList());
    }


    public List<JobDTO> getRecommendation(UUID id) {
        Set<Skill> skills = profileRepository.findSkillsByProfileId(id);
        List<Job> jobs = jobRepository.findJobsBySkills(skills);
        for(Skill s:skills){
            System.out.println(s.getName());
        }

        List<JobDTO> result = sortJobsBySkillMatch(skills, jobs);
        return result;
//        return jobs.stream().map(job -> new JobDTO(
//                job,
//                job.getCompany(), // Ensure job.getCompany() is not null
//                job.getSkills().stream().map(SkillDTO::new).collect(Collectors.toSet())
//        )).collect(Collectors.toList());

    }

    public Set<SkillDTO> getSkillsByProfileId(UUID id) {
        Profile profile = profileRepository.findByUserId(id);
        if(profile==null){
            throw  new RuntimeException("Profile not found with the id : " + id);
        }
        Set<Skill> skills = profileRepository.findSkillsByProfileId(id);
        return skills.stream().map(SkillDTO::new).collect(Collectors.toSet());
    }

    public ProfileDTO addExperienceToProfile(UUID profileId, ExperienceRequest experienceRequest) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new RuntimeException("Profile not found with the id : " + profileId));

        Experience experience = new Experience(experienceRequest);
        experienceRepository.save(experience);
        profile.getExperiences().add(experience);
        profile = profileRepository.save(profile);
        return new ProfileDTO(
                profile.getId(),
                profile.getName(),
                profile.getBio(),
                profile.getEmail(),
                profile.getPhoneNumber(),
                profile.getSkills().stream().map(SkillDTO::new).collect(Collectors.toSet()),
                profile.getAddress(),
                profile.getExperiences().stream().map(ExperienceDTO::new).collect(Collectors.toSet()),
                profile.getEducations().stream().map(EducationDTO::new).collect(Collectors.toSet())
        );
    }


    public ProfileDTO deleteEducationFromProfile(UUID profile_id, UUID educationID){
        Profile profile = profileRepository.findById(profile_id)
                .orElseThrow(() -> new RuntimeException("Profile not found with the id : " + profile_id));
        Education education = educationRepository.findById(educationID)
                .orElseThrow(() -> new RuntimeException("Education not found with the id : " + educationID));
        profile.getEducations().remove(education);
        educationRepository.delete(education);
        profileRepository.save(profile);
        return new ProfileDTO(
                profile.getId(),
                profile.getName(),
                profile.getBio(),
                profile.getEmail(),
                profile.getPhoneNumber(),
                profile.getSkills().stream().map(SkillDTO::new).collect(Collectors.toSet()),
                profile.getAddress(),
                profile.getExperiences().stream().map(ExperienceDTO::new).collect(Collectors.toSet()),
                profile.getEducations().stream().map(EducationDTO::new).collect(Collectors.toSet())
        );
    }


    public ProfileDTO addEducationToProfile(UUID profileId, EducationRequest educationRequest) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new RuntimeException("Profile not found with the id : " + profileId));
        System.out.println("Adding education to profile");

        Education education = new Education(educationRequest);
        Education gg = educationRepository.save(education);
        System.out.println("Education saved");

        Set<Education> educations = profile.getEducations();
        System.out.println("Got educations");
        educations.add(gg);
        System.out.println("Added education to educations");
        profile.setEducations(educations);
        System.out.println("Set educations to profile");
        profile = profileRepository.save(profile);
        System.out.println("Saved profile");
        return new ProfileDTO(
                profile.getId(),
                profile.getName(),
                profile.getBio(),
                profile.getEmail(),
                profile.getPhoneNumber(),
                profile.getSkills().stream().map(SkillDTO::new).collect(Collectors.toSet()),
                profile.getAddress(),
                profile.getExperiences().stream().map(ExperienceDTO::new).collect(Collectors.toSet()),
                profile.getEducations().stream().map(EducationDTO::new).collect(Collectors.toSet())
        );
    }

    public ProfileDTO deleteExperienceFromProfile(UUID profile_id, UUID experienceID){
        Profile profile = profileRepository.findById(profile_id)
                .orElseThrow(() -> new RuntimeException("Profile not found with the id : " + profile_id));
        Experience experience = experienceRepository.findById(experienceID)
                .orElseThrow(() -> new RuntimeException("Experience not found with the id : " + experienceID));
        profile.getExperiences().remove(experience);
        profileRepository.save(profile);
        experienceRepository.delete(experience);
        return new ProfileDTO(
                profile.getId(),
                profile.getName(),
                profile.getBio(),
                profile.getEmail(),
                profile.getPhoneNumber(),
                profile.getSkills().stream().map(SkillDTO::new).collect(Collectors.toSet()),
                profile.getAddress(),
                profile.getExperiences().stream().map(ExperienceDTO::new).collect(Collectors.toSet()),
                profile.getEducations().stream().map(EducationDTO::new).collect(Collectors.toSet())
        );
    }

    public ProfileDTO addSkillToProfile(UUID profileId, SkillRequest skillRequest) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new RuntimeException("Profile not found with the id : " + profileId));

        if(skillRepository.findByName(skillRequest.getName()) != null){
            System.out.println("Skill already exists");
            Skill skill = skillRepository.findByName(skillRequest.getName());
            Set<Skill> skillSet = profile.getSkills();
            skillSet.add(skill);
            profile.setSkills(skillSet);
            profileRepository.save(profile);
            return new ProfileDTO(
                    profile.getId(),
                    profile.getName(),
                    profile.getBio(),
                    profile.getEmail(),
                    profile.getPhoneNumber(),
                    profile.getSkills().stream().map(SkillDTO::new).collect(Collectors.toSet()),
                    profile.getAddress(),
                    profile.getExperiences().stream().map(ExperienceDTO::new).collect(Collectors.toSet()),
                    profile.getEducations().stream().map(EducationDTO::new).collect(Collectors.toSet())
            );
        }
        Skill skill = new Skill(skillRequest);
        skillRepository.save(skill);
        profile.getSkills().add(skill);
        profile = profileRepository.save(profile);
        profileRepository.save(profile);

        return new ProfileDTO(
                profile.getId(),
                profile.getName(),
                profile.getBio(),
                profile.getEmail(),
                profile.getPhoneNumber(),
                profile.getSkills().stream().map(SkillDTO::new).collect(Collectors.toSet()),
                profile.getAddress(),
                profile.getExperiences().stream().map(ExperienceDTO::new).collect(Collectors.toSet()),
                profile.getEducations().stream().map(EducationDTO::new).collect(Collectors.toSet())
        );
    }

    public ProfileDTO deleteSkillFromProfile(UUID id, UUID skillId) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found with the id : " + id));
        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new RuntimeException("Skill not found with the id : " + skillId));
        profile.getSkills().remove(skill);
        profileRepository.save(profile);
        return new ProfileDTO(
                profile.getId(),
                profile.getName(),
                profile.getBio(),
                profile.getEmail(),
                profile.getPhoneNumber(),
                profile.getSkills().stream().map(SkillDTO::new).collect(Collectors.toSet()),
                profile.getAddress(),
                profile.getExperiences().stream().map(ExperienceDTO::new).collect(Collectors.toSet()),
                profile.getEducations().stream().map(EducationDTO::new).collect(Collectors.toSet())
        );
    }
}
