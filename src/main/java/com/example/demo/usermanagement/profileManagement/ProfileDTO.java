package com.example.demo.usermanagement.profileManagement;

import com.example.demo.usermanagement.profileManagement.helperModels.Address;
import lombok.Data;
import java.util.List;
import java.util.UUID;

@Data
public class ProfileDTO {

    private UUID id;
    private String name;
    private String bio;
    private String email;
    private String phoneNumber;
    private String userType;
    private String photo;
    private String resume;

    private List<String> skills;
    private AddressDTO address;

    private String linkedInProfile;
    private String portfolio;

    private List<ExperienceDTO> experiences;
    private List<EducationDTO> education;
    private List<CertificationDTO> certifications;

    private List<String> languages;

    private String availabilityStatus;
    private String expectedSalary;
    private String workAuthorization;
    private boolean profileVisibility;

    private List<ReferenceDTO> references;

    @Data
    // Nested DTOs for Address, Experience, Education, Certification, Reference
    public static class AddressDTO {
        private String addressLine1;
        private String city;
        private String country;

        public AddressDTO(String addressLine1, String city, String country) {
            this.addressLine1 = addressLine1;
            this.city = city;
            this.country = country;
        }


    }

    @Data
    public static class ExperienceDTO {
        private String jobTitle;
        private String companyName;
        private String duration;
        private String description;

        public ExperienceDTO(String jobTitle, String companyName, String duration, String description) {
            this.jobTitle = jobTitle;
            this.companyName = companyName;
            this.duration = duration;
            this.description = description;
        }
    }
    @Data
    public static class EducationDTO {
        private String degree;
        private String institution;
        private int graduationYear;

        public EducationDTO(String degree, String institution, int graduationYear) {
            this.degree = degree;
            this.institution = institution;
            this.graduationYear = graduationYear;
        }
    }

    public static class CertificationDTO {
        private String name;
        private String issuingAuthority;
        private String dateOfCompletion; // String or LocalDate
    }

    public static class ReferenceDTO {
        private String name;
        private String contactInfo;
        private String relationship;
    }


    public ProfileDTO(Profile profile ) {
        this.id = profile.getId();
        this.name = profile.getName();
        this.bio = profile.getBio();
        this.email = profile.getEmail();
        this.phoneNumber = profile.getPhoneNumber();
        this.userType = profile.getUserType();
        this.photo = profile.getPhoto();
        this.resume = profile.getResume();
//        this.address = addressRequest;
//        this.skills = profile.getSkills(); // Assuming Profile has a `getSkills` method.

        if (profile.getAddress() != null) {
            Address addressEntity = profile.getAddress();
            this.address = new AddressDTO(addressEntity.getAddressLine1(), addressEntity.getCity(), addressEntity.getCountry());
        }
//
//        this.linkedInProfile = profile.getLinkedInProfile();
//        this.portfolio = profile.getPortfolio();
//
//        this.experiences = profile.getExperiences() != null
//                ? profile.getExperiences().stream()
//                .map(exp -> new ExperienceDTO(exp.getJobTitle(), exp.getCompanyName(), exp.getDuration(), exp.getDescription()))
//                .collect(Collectors.toList())
//                : null;
//
//        this.education = profile.getEducation() != null
//                ? profile.getEducation().stream()
//                .map(edu -> new EducationDTO(edu.getDegree(), edu.getInstitution(), edu.getGraduationYear()))
//                .collect(Collectors.toList())
//                : null;
//
//        this.certifications = profile.getCertifications() != null
//                ? profile.getCertifications().stream()
//                .map(cert -> new CertificationDTO(cert.getName(), cert.getIssuingAuthority(), cert.getDateOfCompletion()))
//                .collect(Collectors.toList())
//                : null;
//
//        this.languages = profile.getLanguages();
//        this.availabilityStatus = profile.getAvailabilityStatus();
//        this.expectedSalary = profile.getExpectedSalary();
//        this.workAuthorization = profile.getWorkAuthorization();
//        this.profileVisibility = profile.isProfileVisibility();
//
//        this.references = profile.getReferences() != null
//                ? profile.getReferences().stream()
//                .map(ref -> new ReferenceDTO(ref.getName(), ref.getContactInfo(), ref.getRelationship()))
//                .collect(Collectors.toList())
//                : null;
    }
}
