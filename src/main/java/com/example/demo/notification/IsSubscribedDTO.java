package com.example.demo.notification;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IsSubscribedDTO {
    private boolean isSubscribed;
    public IsSubscribedDTO(boolean isSubscribed) {
        this.isSubscribed = isSubscribed;
    }
}
