package com.example.demo.notification;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailNotificationStrategy implements NotificationStrategy {
//    @Autowired
//    private JavaMailSender mailSender;

    @Autowired
    private EmailSender emailSender;


    @Override
    public void send(Notification notification) {
        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(notification.getRecipientId());
        message.setSubject("New Job Notification");
//        message.setText(notification.getMessage());
        SimpleMailMessage simpleMailMessage = notification.getSimpleMailMessage();
        emailSender.sendEmail(simpleMailMessage);
        System.out.println("Email sent to " + notification.getRecipientId());
        System.out.println("Message: " + notification.getMessage());
//        mailSender.send(message);
    }
}