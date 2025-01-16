package com.example.demo.usermanagement.profileManagement.resume;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/resume")
public class ResumeController {


    @Autowired
    ResumeService resumeService;

    @PostMapping("/create/{profileId}")
    public ResumeDTO addResume(@PathVariable UUID profileId , @RequestBody ResumeRequest resumeRequest){
        System.out.println(profileId);
        System.out.println(resumeRequest);
//        return null;
        return resumeService.addResume(resumeRequest, profileId);
    }

    @GetMapping("/profile/{profileId}")
    public List<ResumeDTO> getResume(@PathVariable UUID profileId){
        return resumeService.getAllResumesByProfileId(profileId);
    }
    @GetMapping("/byid/{id}")
    public ResumeDTO getResumeById(@PathVariable UUID id){
        return resumeService.getResumeById(id);
    }
    @PutMapping("/update/{id}")
    public ResumeDTO updateResume(@PathVariable UUID id, ResumeRequest resumeRequest){
        return resumeService.updateResume(resumeRequest, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteResume(@PathVariable UUID id){
        resumeService.deleteResume(id);
    }

}
