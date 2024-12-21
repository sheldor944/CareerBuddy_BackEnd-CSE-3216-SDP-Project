package com.example.demo.notification;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleMailMessage {

    private String email;
    private String message;
    private String subject;

    public SimpleMailMessage() {
    }

    public SimpleMailMessage(String email, String message, String subject) {
        this.email = email;
        this.message = message;
        this.subject = subject;
    }

}
