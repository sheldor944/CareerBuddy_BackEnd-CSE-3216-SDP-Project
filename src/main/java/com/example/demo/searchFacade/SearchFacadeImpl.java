package com.example.demo.searchFacade;

import com.example.demo.jobmanagement.job.JobDTO;
import com.example.demo.jobmanagement.job.JobService;
import com.example.demo.jobmanagement.companymanagement.CompanyDTO;
import com.example.demo.jobmanagement.companymanagement.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchFacadeImpl implements SearchFacade {
    @Autowired
    private JobService jobService;
    @Autowired
    private CompanyService companyService;

    @Override
    public SearchResults search(SearchCriteria criteria) {
        List<JobDTO> jobs = null;
        List<CompanyDTO> companies = null;

        System.out.println("here with criteria: " + criteria);
        // Delegate to JobService if job-related criteria are present
        if (isJobSearchCriteria(criteria)) {
            System.out.println(criteria);
            jobs = jobService.searchJobs(
                   criteria
            );
        }

        // Delegate to CompanyService if company-related criteria are present
        if (isCompanySearchCriteria(criteria)) {
            companies = companyService.searchCompanies(
                    criteria.getCompanyName(),
                    criteria.getLocation(),
                    criteria.getDomain()
            );
        }

        return new SearchResults(jobs, companies);
    }

    private boolean isJobSearchCriteria(SearchCriteria criteria) {
        return criteria.getJobTitle() != null ||
                criteria.getJobDescription() != null ||
                criteria.getLocation() != null ||
                criteria.getMinExperience() != null ||
                criteria.getMaxExperience() != null ||
                criteria.getJobType() != null ||
                criteria.getMinSalary() != null ||
                criteria.getMaxSalary() != null ||
                criteria.getJobDeadline() != null;
    }

    private boolean isCompanySearchCriteria(SearchCriteria criteria) {
        return criteria.getCompanyName() != null ||
                criteria.getLocation() != null ||
                criteria.getDomain() != null ||
                criteria.getPhoneNumber() != null ||
                criteria.getEmail() != null ||
                criteria.getWebsite() != null ||
                criteria.getCompanyDescription() != null ||
                criteria.getRegistrationYear() != null ||
                criteria.getFoundationYear() != null ;

    }
}