package com.example.demo.usermanagement.profileManagement.helperModels;

import com.example.demo.usermanagement.profileManagement.Profile;
import com.example.demo.usermanagement.profileManagement.ProfileRequest;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Entity
@Data
@NoArgsConstructor
public class Education {
    @Id
    @GeneratedValue
    private UUID id ;
    private String degree;
    private String institution;
    private int graduationYear;

    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false)
    @JsonBackReference // Prevents serialization loop by marking the child side
    private Profile profile;

    public Education(ProfileRequest.EducationRequest request) {
        this.degree = request.getDegree();
        this.institution = request.getInstitution();
        this.graduationYear = request.getGraduationYear();
    }


}