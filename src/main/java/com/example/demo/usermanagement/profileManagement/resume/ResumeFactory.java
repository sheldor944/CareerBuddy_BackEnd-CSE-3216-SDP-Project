package com.example.demo.usermanagement.profileManagement.resume;

import com.example.demo.usermanagement.profileManagement.Profile;
import com.example.demo.usermanagement.profileManagement.education.Education;
import com.example.demo.usermanagement.profileManagement.experience.Experience;
import com.example.demo.usermanagement.profileManagement.skill.Skill;

import java.util.Set;
import java.util.UUID;

public class ResumeFactory {

    public static Resume createResume(ResumeRequest resumeRequest, Profile profile,
                                      Set<Education> educations, Set<Experience> experiences,
                                      Set<Skill> skills) {
        Resume resume = new Resume(resumeRequest);
        resume.setProfile(profile);
        resume.setEducations(educations);
        resume.setExperiences(experiences);
        resume.setSkills(skills);
        return resume;
    }
}
