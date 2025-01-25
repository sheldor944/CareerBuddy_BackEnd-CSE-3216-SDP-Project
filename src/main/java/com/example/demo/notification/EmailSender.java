package com.example.demo.notification;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
@Service
public class EmailSender {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(SimpleMailMessage simpleMailMessage) {
        try {
            // Create a MimeMessage for more flexibility
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            // Set email details from the SimpleMailMessage
            helper.setFrom("careerbuddy.73@gmail.com");
            helper.setTo(simpleMailMessage.getEmail());
            helper.setSubject(simpleMailMessage.getSubject());

            // Construct email body
            String emailBody = String.format(
                    "Greetings\n" +
                            "Hope this email finds you well.\n\n" +
                            "%s\n\n\n\n\n\n\n" +
                            "Team CareerBuddy",
                    simpleMailMessage.getMessage()
            );
            helper.setText(emailBody);

            // Send the email
            mailSender.send(mimeMessage);

            // Optional: Print sender and receiver emails
            System.out.println("From: careerbuddy.73@gmail.com, To: " + simpleMailMessage.getEmail());
        } catch (MessagingException e) {
            // You might want to use a proper logging mechanism instead of printing
            e.printStackTrace();
            // Or throw a custom exception
            // throw new EmailSendingException("Failed to send email", e);
        }
    }
}