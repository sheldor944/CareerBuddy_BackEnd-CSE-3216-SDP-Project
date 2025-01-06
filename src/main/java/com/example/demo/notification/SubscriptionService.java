package com.example.demo.notification;

import com.example.demo.jobmanagement.companymanagement.Company;
import com.example.demo.jobmanagement.companymanagement.CompanyDTO;
import com.example.demo.jobmanagement.companymanagement.CompanyRepository;
import com.example.demo.jobmanagement.companymanagement.CompanyService;
import com.example.demo.usermanagement.models.User;
import com.example.demo.usermanagement.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SubscriptionService {
    @Autowired
    private EmailNotificationStrategy emailNotificationStrategy;
    @Autowired
    private UserSubscriptionRepository userSubscriptionRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CompanyService companyService;

    public UserSubscriptionDTO subscriptionHelper(SubscribeRequest subscribeRequest){

        Company company = companyRepository.findById(subscribeRequest.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Company not found with the id : " + subscribeRequest.getCompanyId()));
        User user = userRepository.findById(subscribeRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with the id : " + subscribeRequest.getUserId()));
        return subscribeToCompany(user, company);

    }

    @Transactional
    public UserSubscriptionDTO subscribeToCompany(User user, Company company) {
        UserSubscription subscription = new UserSubscription();
        subscription.setUser(user);
        subscription.setSubscribedCompany(company);

        // Add default notification strategy
        subscription.addNotificationStrategy(emailNotificationStrategy);

        // Add subscriber to company (assuming Company class has addSubscriber method)
//        company.addSubscriber(subscription);
//        companyRepository.save(company);


        // Save subscription to the database
        UserSubscription userSubscription =  userSubscriptionRepository.save(subscription);
        return new UserSubscriptionDTO(userSubscription.getId(), userSubscription.getUser().getId(), userSubscription.getSubscribedCompany().getId());
    }

    public UserSubscriptionDTO unsubscriptionHelper(SubscribeRequest subscribeRequest) {
        Company company = companyRepository.findById(subscribeRequest.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Company not found with the id : " + subscribeRequest.getCompanyId()));
        User user = userRepository.findById(subscribeRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with the id : " + subscribeRequest.getUserId()));
        return unsubscribeFromCompany(user, company);
    }

    private UserSubscriptionDTO unsubscribeFromCompany(User user, Company company) {
        UserSubscription subscription = userSubscriptionRepository.findByUserAndSubscribedCompany(user, company)
                .orElseThrow(() -> new RuntimeException("Subscription not found for user : " + user.getId() + " and company : " + company.getId()));
        userSubscriptionRepository.delete(subscription);
        return new UserSubscriptionDTO(subscription.getId(), subscription.getUser().getId(), subscription.getSubscribedCompany().getId());
    }

    public List<UserSubscriptionDTO> getSubscriptionsByUserId(UUID id) {
        List<UserSubscription> userSubscriptionList = userSubscriptionRepository.findByUser_Id(id);
        List<UserSubscriptionDTO> userSubscriptionDTOList = new ArrayList<>();
        for( UserSubscription userSubscription : userSubscriptionList){
            CompanyDTO companyDTO = new CompanyDTO(userSubscription.getSubscribedCompany());
            userSubscriptionDTOList.add(
                    new UserSubscriptionDTO(
                            userSubscription.getId(),
                            userSubscription.getUser().getId(),
                            userSubscription.getSubscribedCompany().getId(),
                            companyDTO
            ));
        }

        return userSubscriptionDTOList;
    }

    public List<UserSubscriptionDTO> getSubscriptionsByCompanyId(UUID id) {
        List<UserSubscription> userSubscriptionList = userSubscriptionRepository.findBySubscribedCompany_Id(id);
        return userSubscriptionList.stream()
                .map(userSubscription -> new UserSubscriptionDTO(
                        userSubscription.getId(),
                        userSubscription.getUser().getId(),
                        userSubscription.getSubscribedCompany().getId()
                ))
                .collect(Collectors.toList());

    }
}