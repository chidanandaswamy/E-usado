package com.stackroute.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailServiceImpl implements EmailService  {

   @Autowired
    private JavaMailSender emailSender;

    @Override
    public void sendMail(String toEmail, String subject, String body, String attachment) throws MessagingException {

        MimeMessage mimeMailMessage = emailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
        mimeMessageHelper.setFrom("e.usado3@gmail.com");
        mimeMessageHelper.setTo(toEmail);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(body);

        FileSystemResource fileSystemResource = new FileSystemResource(new File(attachment));

        mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);

        emailSender.send(mimeMailMessage);

        System.out.println("mail sent successfully");
    }


//    public void sendSimpleEmail(String toEmail, String subject, String body, String attachments) {
//
//         SimpleMailMessage message = new SimpleMailMessage();
//
//         message.setFrom("e.usado3@gmail.com");
//         message.setTo(toEmail);
//         message.setSubject(subject);
//        message.setText(body);
//
//        emailSender.send(message);
//        System.out.println("mail sent");
//    }



}
