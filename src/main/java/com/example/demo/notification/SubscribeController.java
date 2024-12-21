package com.example.demo.notification;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
