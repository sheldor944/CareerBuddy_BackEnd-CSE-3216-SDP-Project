//package com.example.demo.usermanagement.profileManagement;
//
//import java.util.ArrayList;
//import com.example.demo.usermanagement.models.User;
//import com.example.demo.usermanagement.profileManagement.helperModels.*;
//import org.springframework.context.annotation.Profile;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ProfileBuilder {
//    private String name;
//    private String bio;
//    private String email;
//    private String phoneNumber;
//    private String userType = "Applicant"; // Default value
//    private String photo;
//    private String resume;
//    private List<String> skills = new ArrayList<>();
//    private Address address;
//    private String linkedInProfile;
//    private String portfolio;
//    private List<Experience> experiences = new ArrayList<>();
//    private List<Education> education = new ArrayList<>();
//    private List<Certification> certifications = new ArrayList<>();
//    private List<String> languages = new ArrayList<>();
//    private String availabilityStatus;
//    private String expectedSalary;
//    private String workAuthorization;
//    private boolean profileVisibility = true;
//    private User user;
//    private List<Reference> references = new ArrayList<>();
//
//    public ProfileBuilder setName(String name) {
//        this.name = name;
//        return this;
//    }
//
//    public ProfileBuilder setBio(String bio) {
//        this.bio = bio;
//        return this;
//    }
//
//    public ProfileBuilder setEmail(String email) {
//        this.email = email;
//        return this;
//    }
//
//    public ProfileBuilder setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//        return this;
//    }
//
//    public ProfileBuilder setUserType(String userType) {
//        this.userType = userType;
//        return this;
//    }
//
//    public ProfileBuilder setPhoto(String photo) {
//        this.photo = photo;
//        return this;
//    }
//
//    public ProfileBuilder setResume(String resume) {
//        this.resume = resume;
//        return this;
//    }
//
//    public ProfileBuilder setSkills(List<String> skills) {
//        this.skills = skills;
//        return this;
//    }
//
//    public ProfileBuilder setAddress(Address address) {
//        this.address = address;
//        return this;
//    }
//
//    public ProfileBuilder setLinkedInProfile(String linkedInProfile) {
//        this.linkedInProfile = linkedInProfile;
//        return this;
//    }
//
//    public ProfileBuilder setPortfolio(String portfolio) {
//        this.portfolio = portfolio;
//        return this;
//    }
//
//    public ProfileBuilder setExperiences(List<Experience> experiences) {
//        this.experiences = experiences;
//        return this;
//    }
//
//    public ProfileBuilder setEducation(List<Education> education) {
//        this.education = education;
//        return this;
//    }
//
//    public ProfileBuilder setCertifications(List<Certification> certifications) {
//        this.certifications = certifications;
//        return this;
//    }
//
//    public ProfileBuilder setLanguages(List<String> languages) {
//        this.languages = languages;
//        return this;
//    }
//
//    public ProfileBuilder setAvailabilityStatus(String availabilityStatus) {
//        this.availabilityStatus = availabilityStatus;
//        return this;
//    }
//
//    public ProfileBuilder setExpectedSalary(String expectedSalary) {
//        this.expectedSalary = expectedSalary;
//        return this;
//    }
//
//    public ProfileBuilder setWorkAuthorization(String workAuthorization) {
//        this.workAuthorization = workAuthorization;
//        return this;
//    }
//
//    public ProfileBuilder setProfileVisibility(boolean profileVisibility) {
//        this.profileVisibility = profileVisibility;
//        return this;
//    }
//
//    public ProfileBuilder setUser(User user) {
//        this.user = user;
//        return this;
//    }
//
//    public ProfileBuilder setReferences(List<Reference> references) {
//        this.references = references;
//        return this;
//    }
//
//    public Profile build() {
//        Profile profile = new Profile();
//        profile.setName(name);
//        profile.setBio(bio);
//        profile.setEmail(email);
//        profile.setPhoneNumber(phoneNumber);
//        profile.setUserType(userType);
//        profile.setPhoto(photo);
//        profile.setResume(resume);
//        profile.setSkills(skills);
//        profile.setAddress(address);
//        profile.setLinkedInProfile(linkedInProfile);
//        profile.setPortfolio(portfolio);
//        profile.setExperiences(experiences);
//        profile.setEducation(education);
//        profile.setCertifications(certifications);
//        profile.setLanguages(languages);
//        profile.setAvailabilityStatus(availabilityStatus);
//        profile.setExpectedSalary(expectedSalary);
//        profile.setWorkAuthorization(workAuthorization);
//        profile.setProfileVisibility(profileVisibility);
//        profile.setUser(user);
//        profile.setReferences(references);
//        return profile;
//    }
//}
