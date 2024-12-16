package com.kodbook.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.kodbook.dto.*;

import jakarta.mail.internet.MimeMessage;


@Service(value = "emailService")
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Override
    public String sendtextEMail(String email) {
        try {
            // Create MimeMessage for HTML content
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            // Set email details
            helper.setSubject("Email Testing from Java SpringBoot");
            helper.setFrom(fromEmail);
            helper.setTo(email);

            // HTML content
            String htmlContent = """
                <html>
                <body>
                    <p>Dear User,</p>
                    <p>We received a request to reset your password. Click the link below to reset it:</p>
                    <a href="https://example.com/reset-password" style="display: inline-block; padding: 10px 20px; font-size: 16px; background-color: #007bff; color: #fff; text-decoration: none; border-radius: 5px;">Reset Password</a>
                    <p>If the link does not work, copy and paste this into your browser: http://localhost:9001/confirmpass</p>
                    <p>If you didn’t request this, please ignore this email.</p>
                    <p>Best regards,<br>Your Company</p>
                </body>
                </html>
                """;

            // Set the email body as HTML
            helper.setText(htmlContent, true); // `true` means HTML content

            // Send email
            mailSender.send(mimeMessage);

            return "Mail sent successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to send email: " + e.getMessage();
        }
    }
    public String sendtextEMailSignup(String email,String username) {
        try {
            // Create MimeMessage for HTML content
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            // Set email details
            helper.setSubject("Email Testing from Java SpringBoot");
            helper.setFrom(fromEmail);
            helper.setTo(email);

            // HTML content
            String htmlContent = 
            	    "<html>" +
            	        "<body style='font-family: Arial, sans-serif; line-height: 1.6; background-color: #f9f9f9; padding: 20px;'>" +
            	            "<div style='max-width: 600px; margin: auto; background: #ffffff; border-radius: 10px; padding: 20px; box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);'>" +
            	                "<h2 style='text-align: center; color: #007bff;'>Welcome to KodBook!</h2>" +
            	                "<p>Dear <b>" + username + "</b>,</p>" +
            	                "<p>Thank you for signing up for <b>KodBook</b>. We're thrilled to have you join our community of passionate learners and developers!</p>" +
            	                "<p>Start exploring KodBook today to connect with others, share your knowledge, and grow your skills.</p>" +
            	                "<p style='text-align: center;'>" +
            	                    "<a href='https://www.google.com/webhp?hl=en&sa=X&ved=0ahUKEwjf67SWl5-KAxWCcWwGHfeXFJIQPAgI' style='display: inline-block; padding: 10px 20px; font-size: 16px; color: #ffffff; background-color: #007bff; text-decoration: none; border-radius: 5px;'>Get Started</a>" +
            	                "</p>" +
            	                "<p>If you have any questions or need assistance, feel free to reach out to us at <a href='mailto:support@kodbook.com' style='color: #007bff;'>support@kodbook.com</a>.</p>" +
            	                "<p>Happy coding!</p>" +
            	                "<p style='font-size: 14px; color: #777;'>Best regards,<br>The KodBook Team</p>" +
            	                "<hr style='border: none; border-top: 1px solid #ddd; margin: 20px 0;'>" +
            	                "<p style='font-size: 12px; color: #999; text-align: center;'>If you didn't sign up for KodBook, please ignore this email.</p>" +
            	            "</div>" +
            	        "</body>" +
            	    "</html>";



            // Set the email body as HTML
            helper.setText(htmlContent, true); // `true` means HTML content

            // Send email
            mailSender.send(mimeMessage);

            return "Mail sent successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to send email: " + e.getMessage();
        }
    }
}
