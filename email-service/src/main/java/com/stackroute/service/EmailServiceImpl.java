package com.stackroute.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl  {

   @Autowired
    private JavaMailSender emailSender;


    public void sendSimpleEmail(String toEmail, String subject, String body) {

         SimpleMailMessage message = new SimpleMailMessage();

         message.setFrom("e.usado3@gmail.com");
         message.setTo(toEmail);
         message.setSubject(subject);
        message.setText(body);

        emailSender.send(message);
        System.out.println("mail sent");
    }


}
