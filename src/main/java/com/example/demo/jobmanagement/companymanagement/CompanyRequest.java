package com.example.demo.jobmanagement.companymanagement;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class CompanyRequest {
    private UUID userId;
    private String companyName;
    private String location;
    private String phoneNumber;
    private String email;
    private String website;
    private String domain;
    private String description;
    private CompanySize size;
    private LocalDateTime foundationYear;
    private String registrationYear;
    private boolean isActive;


}
