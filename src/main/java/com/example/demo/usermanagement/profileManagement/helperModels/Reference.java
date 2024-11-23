package com.example.demo.usermanagement.profileManagement.helperModels;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class Reference {
    private String name;
    private String contactInfo;
    private String relationship;
}