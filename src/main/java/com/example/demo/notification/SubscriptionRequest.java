package com.example.demo.notification;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public class SubscriptionRequest {
    private UUID userId;
    private UUID companyId;
}
