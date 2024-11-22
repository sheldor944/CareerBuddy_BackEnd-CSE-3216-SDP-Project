    package com.example.demo.jobmanagement;

    import com.example.demo.jobmanagement.companymanagement.Company;
    import jakarta.persistence.*;
    import lombok.Data;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    import java.time.LocalDateTime;
    import java.util.Date;
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
        private String location;
        private double experience;
        private String jobType;
        @Column(name = "deadline")
        private LocalDateTime deadline;
        private double salary;
        //  Role
        //- Company
        //- Description
        //- Location
        //- Salary
        //- Experience (years)
        //- Total Applicants
        //- Duration (Part time/ full time)
        //- Deadline of application
        //- Company Logo (pic)

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

        public Job(Company company, String title, String description, String location, double experience, String jobType, LocalDateTime deadline, double salary) {
            this.company = company;
            this.title = title;
            this.description = description;
            this.location = location;
            this.experience = experience;
            this.jobType = jobType;
            this.deadline = deadline;
            this.salary = salary;
        }
    }
