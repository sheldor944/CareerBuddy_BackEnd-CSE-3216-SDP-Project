package com.example.demo.usermanagement.profileManagement.research;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class ResearchController {

    @Autowired
    ResearchService researchService;

    @PostMapping("/research/{profileId}")
    public ResearchDTO addResearch(@PathVariable UUID profileId, @RequestBody ResearchRequest researchRequest) {
        return researchService.addResearch(profileId, researchRequest);
    }


}
