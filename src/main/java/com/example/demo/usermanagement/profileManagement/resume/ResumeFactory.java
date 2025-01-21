package com.example.demo.usermanagement.profileManagement.resume;

import com.example.demo.usermanagement.profileManagement.Profile;
import com.example.demo.usermanagement.profileManagement.education.Education;
import com.example.demo.usermanagement.profileManagement.experience.Experience;
import com.example.demo.usermanagement.profileManagement.skill.Skill;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class ResumeFactory {

    public static Object createResume(ResumeRequest resumeRequest, Profile profile,
                                       Set<Education> educations, Set<Experience> experiences,
                                       Set<Skill> skills) {

        if(resumeRequest.getResumeType().equals("student")) {
            Resume resume = new Resume(resumeRequest);
            resume.setProfile(profile);
            resume.setEducations(educations);
            resume.setExperiences(experiences);
            resume.setSkills(skills);
            return resume;
        } else {
            ProfessionalResume resume = new ProfessionalResume(resumeRequest);
            resume.setProfile(profile);
            resume.setEducations(educations);
            resume.setExperiences(experiences);
            resume.setSkills(skills);
            resume.setResearches(null);
            return resume;
        }

    }
}
