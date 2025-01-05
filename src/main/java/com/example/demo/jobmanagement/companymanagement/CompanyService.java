package com.example.demo.jobmanagement.companymanagement;

import com.example.demo.usermanagement.models.User;
import com.example.demo.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    UserRepository userRepository;

    public CompanyDTO createCompany(CompanyRequest companyRequest){
//        Company company = new Company(companyRequest);
        User user = userRepository.findById(companyRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + companyRequest.getUserId()));
        Company company = new CompanyBuilder()
                .fromRequest(companyRequest)
                .user(user)
                .build();
        companyRepository.save(company);
        return new CompanyDTO(user.getId(),company);
    }

    public List<CompanyDTO> getAllCompany() {
        List<Company> companies = companyRepository.findAll();

        return companies.stream()
                .map(company -> new CompanyDTO(
                        company.getUser().getId(),
                        company
                ))
                .collect(Collectors.toList());

//        return companies.stream()
//                .map(company -> new CompanyDTO(
//                        company.getName(),
//                        company.getLocation(),
//                        company.getPhoneNumber(),
//                        company.getEmail(),
//                        company.getDomain(),
//                        company.getWebsite(),
//                        company.getDescription(),
//                        company.getSize(),
//                        company.getFoundationYear(),
//                        company.getRegistrationYear(),
//                        company.isActive(),
//                        company.getId()
//                ))
//                .collect(Collectors.toList());
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
                        company.getName(),
                        company.getLocation(),
                        company.getPhoneNumber(),
                        company.getEmail(),
                        company.getDomain(),
                        company.getWebsite(),
                        company.getDescription(),
                        company.getSize(),
                        company.getFoundationYear(),
                        company.getRegistrationYear(),
                        company.isActive(),
                        company.getId()
                ))
                .collect(Collectors.toList());

    }

    public List<CompanyDTO> getCompaniesByUserId(UUID userId) {
        List<Company> companies = companyRepository.findByUserId(userId);
        return companies.stream()
                .map(company -> new CompanyDTO(
                        company.getName(),
                        company.getLocation(),
                        company.getPhoneNumber(),
                        company.getEmail(),
                        company.getDomain(),
                        company.getWebsite(),
                        company.getDescription(),
                        company.getSize(),
                        company.getFoundationYear(),
                        company.getRegistrationYear(),
                        company.isActive(),
                        company.getId()
                ))
                .collect(Collectors.toList());
    }
}
