package com.example.demo.usermanagement.profileManagement;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;
import java.util.UUID;

@Data
public class ProfileRequest {

    @NotNull(message = "User ID is required")
    private UUID user_id ;
    private String name;
    private String bio;
    private String email;
    private String phoneNumber;
    private String userType = "Applicant"; // Default value
    private String photo; // Could be a URL or Blob
    private String resume; // URL or Blob

    private List<String> skills; // Limit to 5
    private AddressRequest address;

    private String linkedInProfile;
    private String portfolio;

    private List<ExperienceRequest> experiences;
    private List<EducationRequest> education;
    private List<CertificationRequest> certifications;

    private List<String> languages;

    private String availabilityStatus;
    private String expectedSalary;
    private String workAuthorization;
    private boolean profileVisibility = true;

    private List<ReferenceRequest> references;

    @Data
    // Nested DTOs for Address, Experience, Education, Certification, Reference
    public static class AddressRequest {
        private String addressLine1;
        private String city;
        private String country;
    }
    @Data
    public static class ExperienceRequest {
        private String jobTitle;
        private String companyName;
        private String duration;
        private String description;
    }
    @Data
    public static class EducationRequest {
        private String degree;
        private String institution;
        private int graduationYear;
    }

    public static class CertificationRequest {
        private String name;
        private String issuingAuthority;
        private String dateOfCompletion; // String or LocalDate
    }

    public static class ReferenceRequest {
        private String name;
        private String contactInfo;
        private String relationship;
    }
}
