package com.example.demo.jobmanagement;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/job")
public class JobController {
    @Autowired
    JobService jobService;

    @PostMapping
    public JobDTO createJob(@RequestBody JobRequest jobRequest){
        return jobService.createJob(jobRequest);
//        return null;
    }

    @GetMapping
    public List<JobDTO> getAllJobs(){
        return jobService.getAllJobs();
    }

    @GetMapping("/{id}")
    public JobDTO getJobById(@PathVariable UUID id){
        return  jobService.getJobByID(id);
    }

    @GetMapping("/search")
    public List<JobDTO> searchJobs(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) Double experience,
            @RequestParam(required = false) String jobType,
            @RequestParam(required = false) Double salary
    ) {
        return jobService.searchJobs(title, description, location, experience, jobType,  salary);
    }
//    @GetMapping("/search")
//    public List<JobDTO> searchJobs(
//            @RequestParam(required = false) String title,
//            @RequestParam(required = false) String description
//    ){
//        return jobService.searchJobs(title, description );
//    }



}
