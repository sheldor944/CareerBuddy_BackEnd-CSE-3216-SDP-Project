package com.example.demo.searchFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private SearchFacade searchFacade;

    @PostMapping
    public SearchResults search(@RequestBody SearchCriteria criteria) {
        System.out.println("\n\nReceived search request with criteria: " + criteria);
//        return null;
        return searchFacade.search(criteria);
    }
}