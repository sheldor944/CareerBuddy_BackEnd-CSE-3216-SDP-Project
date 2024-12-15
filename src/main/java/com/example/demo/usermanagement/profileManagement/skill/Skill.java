package com.example.demo.usermanagement.profileManagement.skill;

import com.example.demo.usermanagement.profileManagement.Profile;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "skills")
@Setter
@Getter
@NoArgsConstructor
public class Skill {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID", updatable = false, nullable = false)
    private UUID id;

    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "skills")
    private Set<Profile> profiles;

    public Skill(SkillRequest skillRequest) {
        this.name = skillRequest.getName();
    }
    public Skill(SkillDTO skillDTO) {
        this.id = skillDTO.getId();
        this.name = skillDTO.getName();
    }
}
