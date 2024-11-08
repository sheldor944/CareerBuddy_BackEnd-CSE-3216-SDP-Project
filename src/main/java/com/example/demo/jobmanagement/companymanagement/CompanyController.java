package com.example.demo.jobmanagement.companymanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/company")

public class CompanyController {

    @Autowired
    CompanyService companyService;
    @PostMapping
    public Company createCompany(@RequestBody CompanyRequest companyRequest){
        return companyService.createCompany(companyRequest);
    }

    @GetMapping
    public List<CompanyDTO> getAllCompanies(){
        return companyService.getAllCompany();
    }
    @GetMapping("/{id}")
    public CompanyDTO getCompanyById(@PathVariable UUID id) {
        return companyService.getCompanyById(id);
    }

    @GetMapping("/search")
    public List<CompanyDTO> searchCompanies(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String domain,
            @RequestParam(required = false) String location
    ){
        return companyService.searchCompanies(name, domain, location);
    }

//    @GetMapping("/search")
//    public List<Company> getCompanyByName(@RequestParam String name){
//        return companyService.getCompanyByName(name);
//    }

}
