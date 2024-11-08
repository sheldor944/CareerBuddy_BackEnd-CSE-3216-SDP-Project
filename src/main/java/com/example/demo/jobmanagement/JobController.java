package com.example.demo.jobmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public JobDTO getJobById(@PathVariable UUID id){
        return  jobService.getJobByID(id);
    }


}
