package com.example.demo.usermanagement.profileManagement.research;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ResearchDTO {

    private UUID id;
    private String title;
    private String publisher;
    private String link;
    public ResearchDTO(Research researchRequest) {
        this.id = researchRequest.getId();
        this.title = researchRequest.getTitle();
        this.publisher = researchRequest.getPublisher();
        this.link = researchRequest.getLink();
    }
}
