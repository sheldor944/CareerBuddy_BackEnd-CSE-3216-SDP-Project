package com.example.demo.jobmanagement;

import com.example.demo.jobmanagement.companymanagement.Company;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "jobs")
@Data
@NoArgsConstructor
public class Job {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;
    private String title;
    private String description;

    public Job(Company company, JobRequest jobRequest){
        this.company = company;
        this.title = jobRequest.getTitle();
        this.description = jobRequest.getDescription();
    }




}
