    package com.example.demo.jobmanagement.job;

    import com.example.demo.jobmanagement.companymanagement.Company;
    import com.example.demo.jobmanagement.jobApplication.JobApplication;
    import com.example.demo.usermanagement.profileManagement.skill.Skill;
    import jakarta.persistence.*;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import java.time.LocalDateTime;
    import java.util.List;
    import java.util.Set;
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
//        @Column(columnDefinition = "TEXT")
        private String location;

        private String jobType;
        private int experience;
        @Column(name = "deadline")
        private LocalDateTime deadline;
        private int salary;

        @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<JobApplication> jobApplications;

        @ManyToMany
        @JoinTable(
                name = "job_skill", // Junction table name
                joinColumns = @JoinColumn(name = "job_id"), // Foreign key to Job
                inverseJoinColumns = @JoinColumn(name = "skill_id") // Foreign key to Skill
        )
        private Set<Skill> skills;


        public Job(Company company, JobRequest jobRequest){
            this.company = company;
            this.title = jobRequest.getTitle();
            this.description = jobRequest.getDescription();
        }
        // New constructor for JobBuilder
        public Job(Company company, String title, String description) {
            this.company = company;
            this.title = title;
            this.description = description;
        }

        public Job(Company company, String title, String description, String location, int experience, String jobType, LocalDateTime deadline, int salary, Set<Skill> skills) {
            this.company = company;
            this.title = title;
            this.description = description;
            this.location = location;
            this.experience = experience;
            this.jobType = jobType;
            this.deadline = deadline;
            this.salary = salary;
            this.skills = skills;
        }
    }
