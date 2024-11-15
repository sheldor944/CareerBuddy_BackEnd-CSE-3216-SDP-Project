package com.example.demo.jobmanagement.companymanagement;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompanyRequest {
    private String companyName;
    private String location;
    private String domain;

}
