package com.example.demo.searchFacade;

import com.example.demo.jobmanagement.JobDTO;
import com.example.demo.jobmanagement.JobService;
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

        // Delegate to JobService if job-related criteria are present
        if (isJobSearchCriteria(criteria)) {
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
        return criteria.getJobTitle() != null || criteria.getJobDescription() != null ||
                criteria.getLocation() != null || criteria.getMinExperience() != -1 ||
                criteria.getJobType() != null || criteria.getMinSalary() != -1;
    }

    private boolean isCompanySearchCriteria(SearchCriteria criteria) {
        return criteria.getCompanyName() != null || criteria.getLocation() != null ||
                criteria.getDomain() != null;
    }
}