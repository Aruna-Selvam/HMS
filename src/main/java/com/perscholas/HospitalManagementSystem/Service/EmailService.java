package com.perscholas.HospitalManagementSystem.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


public interface EmailService{
    public void sendEmail(String to, String subject, String body) ;

}
