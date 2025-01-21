package com.example.demo.usermanagement.profileManagement.resume;

import com.example.demo.usermanagement.profileManagement.Profile;
import com.example.demo.usermanagement.profileManagement.ProfileRepository;
import com.example.demo.usermanagement.profileManagement.education.Education;
import com.example.demo.usermanagement.profileManagement.education.EducationDTO;
import com.example.demo.usermanagement.profileManagement.education.EducationRepository;
import com.example.demo.usermanagement.profileManagement.experience.Experience;
import com.example.demo.usermanagement.profileManagement.experience.ExperienceDTO;
import com.example.demo.usermanagement.profileManagement.experience.ExperienceRepository;

import com.example.demo.usermanagement.profileManagement.skill.Skill;
import com.example.demo.usermanagement.profileManagement.skill.SkillDTO;
import com.example.demo.usermanagement.profileManagement.skill.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ResumeService {
    @Autowired
    ResumeRepository resumeRepository;
    @Autowired
    ProfileRepository profileRepository;
    @Autowired
    EducationRepository educationRepository;
    @Autowired
    ExperienceRepository experienceRepository;
    @Autowired
    SkillRepository skillRepository;
    @Autowired
    ResumeRepositoryFactory resumeRepositoryFactory;





    public ResumeDTO addResume(ResumeRequest resumeRequest, UUID profileId) {
        Profile profile = profileRepository.findById(profileId).orElseThrow(() -> new RuntimeException("Profile not found"));



        Set<Education> educations = new HashSet<>();
        Set<Experience> experiences = new HashSet<>();
        Set<Skill> skills = new HashSet<>();
        for (UUID educationId : resumeRequest.getEducations()) {
            Education education = educationRepository.getReferenceById(educationId);
            educations.add(education);
        }
        for(UUID experienceID : resumeRequest.getExperiences()){
            Experience experience = experienceRepository.getReferenceById(experienceID);
            experiences.add(experience);
        }
        for(UUID skillID : resumeRequest.getSkills()){
            Skill skill = skillRepository.getReferenceById(skillID);
            skills.add(skill);
        }
        System.out.println("here1");
        Object resume = ResumeFactory.createResume(resumeRequest, profile, educations, experiences, skills);
        System.out.println("here");
//        Resume resume = new Resume(resumeRequest);
//        resume.setProfile(profile);
//        resume.setEducations(educations);
//        resume.setExperiences(experiences);
//        resume.setSkills(skills);

        JpaRepository  repo = resumeRepositoryFactory.createRepository(resumeRequest.getResumeType());
        repo.save(resume);
        return new ResumeDTO(resume);
    }

    public ResumeDTO getResumeById(UUID resumeId) {
        Resume resume = resumeRepository.findById(resumeId).orElseThrow(() -> new RuntimeException("Resume not found"));
        return new ResumeDTO(resume);
    }
    public List<ResumeDTO> getAllResumesByProfileId(UUID profileId) {
        List<Resume> resumes = resumeRepository.findByProfile_Id(profileId);
        return resumes.stream().map(ResumeDTO::new).collect(Collectors.toList());
    }
    public void deleteResume(UUID resumeId) {
        Resume resume = resumeRepository.findById(resumeId).orElseThrow(() -> new RuntimeException("Resume not found"));
        resumeRepository.deleteById(resumeId);
    }
    public ResumeDTO updateResume(ResumeRequest resumeRequest, UUID resumeId) {
        Resume resume = resumeRepository.findById(resumeId).orElseThrow(() -> new RuntimeException("Resume not found"));
        resume.setResumeName(resumeRequest.getResumeName());
        resume.setJobTitle(resumeRequest.getJobTitle());
        resume.setSummary(resumeRequest.getSummary());
        resume.setThemeColor(resumeRequest.getThemeColor());
        resume.setFirstName(resumeRequest.getFirstName());
        resume.setLastName(resumeRequest.getLastName());
        resume.setEmail(resumeRequest.getEmail());
        resume.setPhoneNumber(resumeRequest.getPhoneNumber());
        resume.setAddress(resumeRequest.getAddress());
        Set<Education> educations = new HashSet<>();
        Set<Experience> experiences = new HashSet<>();
        Set<Skill> skills = new HashSet<>();
        for (UUID id : resumeRequest.getEducations()) {
            Education education = educationRepository.getReferenceById(id);
            educations.add(education);
        }
        for(UUID id : resumeRequest.getExperiences()){
            Experience experience = experienceRepository.getReferenceById(id);
            experiences.add(experience);
        }
        for(UUID id : resumeRequest.getSkills()){
            Skill skill = skillRepository.getReferenceById(id);
            skills.add(skill);
        }
        resume.setEducations(educations);
        resume.setExperiences(experiences);
        resume.setSkills(skills);
        resumeRepository.save(resume);
        return new ResumeDTO(resume);

    }
}
