package com.example.demo.jobmanagement.companymanagement;

import com.example.demo.jobmanagement.Job;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "companies")
@Data
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID", updatable = false, nullable = false)
    private UUID id;

    private String name ;
    private String location;
    private String domain;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Job> jobs;

    public Company(CompanyRequest companyRequest){
        this.name = companyRequest.getCompanyName();
        this.domain = companyRequest.getDomain();
        this.location = companyRequest.getLocation();
    }

}
