package com.perscholas.HospitalManagementSystem.config;

import com.perscholas.HospitalManagementSystem.Service.EmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class config {
    @Bean
    public EmailService emailService() {
        return new EmailService() {
            @Override
            public void sendEmail(String to, String subject, String body) {

            }
        };
    }
}
