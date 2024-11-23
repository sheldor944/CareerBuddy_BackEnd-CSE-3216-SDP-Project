package com.example.demo.usermanagement.profileManagement.helperModels;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
public class Experience {
    private String jobTitle;
    private String companyName;
    private String duration;
    private String description;

}