package com.perscholas.HospitalManagementSystem.service;
import com.perscholas.HospitalManagementSystem.Service.EmailServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmailServiceImplTest {

    @Mock
    private JavaMailSender mailSender;

    @InjectMocks
    private EmailServiceImpl emailService;

    @Test
    @DisplayName("Test For Sending Email ")
    public void testSendEmail() {
        String to = "a@gmail.com";
        String subject = "Subject";
        String body = " Body";

        emailService.sendEmail(to, subject, body);

        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));

    }
}
