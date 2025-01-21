package com.example.demo.jobmanagement.job;


import com.example.demo.usermanagement.profileManagement.Profile;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "saved_jobs")
@Getter
@Setter
@NoArgsConstructor
public class SavedJobs {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id", nullable = false, foreignKey = @ForeignKey(name = "fk_saved_jobs_job"))
    private Job job;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", nullable = false, foreignKey = @ForeignKey(name = "fk_saved_jobs_profile"))
    private Profile profile;

    public SavedJobs(Job job, Profile profile) {
        this.job = job;
        this.profile = profile;
    }



}
