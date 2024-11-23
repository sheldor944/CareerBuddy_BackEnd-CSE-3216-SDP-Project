package com.example.demo.usermanagement.profileManagement;

import com.example.demo.usermanagement.models.User;
import com.example.demo.usermanagement.profileManagement.helperModels.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "profiles")
@Data
@Setter
@NoArgsConstructor
public class Profile {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private String bio;
    private String email;
    private String phoneNumber;
    private String userType = "Applicant"; // Default value
    private String photo; // Could be a URL or Blob
    private String resume; // URL or Blob
//
//    private List<String> skills = new ArrayList<>(); // Limit to 5
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")

    private Address address;
    private String linkedInProfile;
    private String portfolio;
//    private List<Experience> experiences;
    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // Prevents serialization loop by marking the parent side
    private List<Education> educationList;

//    private List<Certification> certifications;
//    private List<String> languages;
//    private List<Reference> references;
//    private String availabilityStatus;
//    private String expectedSalary;
//    private String workAuthorization;
//    private boolean profileVisibility = true;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    public Profile(ProfileRequest profileRequest, User userEntity) {
        this.name = profileRequest.getName();
        this.bio = profileRequest.getBio();
        this.email = profileRequest.getEmail();
        this.phoneNumber = profileRequest.getPhoneNumber();
        this.userType = profileRequest.getUserType();
        this.photo = profileRequest.getPhoto();
        this.resume = profileRequest.getResume();
//        this.skills = profileRequest.getSkills();
//        this.address = addressEntity; // Address entity must already be created and passed in
//        this.experiences = experienceEntity;
//        this.educationList = educationEntity;
        this.linkedInProfile = profileRequest.getLinkedInProfile();
        this.portfolio = profileRequest.getPortfolio();
//        this.languages = profileRequest.getLanguages();
//        this.availabilityStatus = profileRequest.getAvailabilityStatus();
//        this.expectedSalary = profileRequest.getExpectedSalary();
//        this.workAuthorization = profileRequest.getWorkAuthorization();
//        this.profileVisibility = profileRequest.isProfileVisibility();
        this.user = userEntity; // User entity must already be created and passed in
    }

}
