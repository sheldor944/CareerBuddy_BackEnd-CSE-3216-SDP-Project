package com.example.demo.jobmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/job")
public class JobController {
    @Autowired
    JobService jobService;

    @PostMapping
    public Job createJob(@RequestBody JobRequest jobRequest){
        return jobService.createJob(jobRequest);
//        return null;
    }

}
