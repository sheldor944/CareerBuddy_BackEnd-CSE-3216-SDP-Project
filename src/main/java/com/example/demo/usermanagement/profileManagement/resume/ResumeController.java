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


    @PostMapping("/modified/{id}")
    public ResumeDTO modifiedResume(@PathVariable UUID id) {
        System.out.println("Resume submitted for review.");
        return resumeService.modifiedResume(id);
    }

    @PostMapping("/finalise/{id}")
    public ResumeDTO finaliseResume(@PathVariable UUID id) {
        return resumeService.finaliseResume(id);
    }

    @PostMapping("/reject/{id}")
    public ResumeDTO rejectResume(@PathVariable UUID id) {
        return resumeService.rejectResume(id);
    }


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
    public ResumeDTO updateResume(@PathVariable UUID id, @RequestBody ResumeRequest resumeRequest){
        return resumeService.updateResume(resumeRequest, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteResume(@PathVariable UUID id){
        resumeService.deleteResume(id);
    }

}
