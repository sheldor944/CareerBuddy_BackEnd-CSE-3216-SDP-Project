package com.example.demo.notification;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/subscribe")

public class SubscribeController {

    @Autowired
    private SubscriptionService subscriptionService;

    @DeleteMapping("/")
    public UserSubscriptionDTO unsubscribe(SubscribeRequest subscribeRequest) {
        return subscriptionService.unsubscriptionHelper(subscribeRequest);
        // unsubscribe user from company
    }

    @PostMapping("/")
    public UserSubscriptionDTO subscribe(SubscribeRequest subscribeRequest) {
        return subscriptionService.subscriptionHelper(subscribeRequest);
        // subscribe user to company
    }

    @GetMapping("/user/{id}")
    public List<UserSubscriptionDTO> getSubscriptionsByUserId(@PathVariable UUID id) {
        return subscriptionService.getSubscriptionsByUserId(id);
    }
    @GetMapping("/company/{id}")
    public List<UserSubscriptionDTO> getSubscriptionsByCompanyId(@PathVariable UUID id) {
        return subscriptionService.getSubscriptionsByCompanyId(id);
    }
}
