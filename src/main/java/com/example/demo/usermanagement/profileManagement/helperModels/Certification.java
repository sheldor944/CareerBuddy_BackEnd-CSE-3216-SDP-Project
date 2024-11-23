package com.example.demo.usermanagement.profileManagement.helperModels;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
public class Certification {
    private String name;
    private String issuingAuthority;
    private LocalDate dateOfCompletion;
}