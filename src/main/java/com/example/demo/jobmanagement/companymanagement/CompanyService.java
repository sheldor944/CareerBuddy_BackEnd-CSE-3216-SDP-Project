package com.example.demo.jobmanagement.companymanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;
    public Company createCompany(CompanyRequest companyRequest){
        Company company = new Company(companyRequest);
        return companyRepository.save(company);
    }

    public List<CompanyDTO> getAllCompany(){
        List<Company> companies = companyRepository.findAll();
        return companies.stream()
                .map(company -> new CompanyDTO(
                        company.getId(),
                        company.getName(),
                        company.getDomain(),
                        company.getLocation()
                ))
                .collect(Collectors.toList());
    }

    public CompanyDTO getCompanyById(UUID uuid){
        Company company = companyRepository.findById(uuid)
                .orElseThrow(() -> new RuntimeException("Company not found with ID: " + uuid));

        return new CompanyDTO(company);
    }

    public List<Company> getCompanyByName(String name){
        return companyRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new RuntimeException("Company not found by : " + name ));
    }

    public List<CompanyDTO> searchCompanies(String name, String domain, String location){

        List<Company> companies =  companyRepository.findByMultipleFields(name, domain, location);
        return companies.stream()
                .map(company -> new CompanyDTO(
                        company.getId(),
                        company.getName(),
                        company.getDomain(),
                        company.getLocation()
                ))
                .collect(Collectors.toList());

    }
}
