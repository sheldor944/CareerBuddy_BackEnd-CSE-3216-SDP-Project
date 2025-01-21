package com.example.demo.usermanagement.profileManagement.research;

import com.example.demo.usermanagement.profileManagement.Profile;
import com.example.demo.usermanagement.profileManagement.resume.ProfessionalResume;
import com.example.demo.usermanagement.profileManagement.resume.Resume;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "researches")
@Setter
@Getter
@NoArgsConstructor
public class Research {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID", updatable = false, nullable = false)
    private UUID id;

    private String title;
    private String publisher;
    private String link;

    @ManyToMany(mappedBy = "researches")
    private Set<Profile> profiles;

    @ManyToMany(mappedBy = "researches")
    private Set<ProfessionalResume> resumes;

    public Research(ResearchRequest researchRequest) {
        this.title = researchRequest.getTitle();
        this.publisher = researchRequest.getPublisher();
        this.link = researchRequest.getLink();
    }
}
