package com.example.demo.notification;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserSubscriptionDTO {
    private UUID id;
    private UUID userId;
    private UUID companyId;

    public UserSubscriptionDTO(UUID id, UUID userId, UUID companyId) {
        this.id = id;
        this.userId = userId;
        this.companyId = companyId;
    }
}
