package com.stackroute.service;

import javax.mail.MessagingException;

public interface EmailService {
    public void sendMail(String toEmail,
                                  String subject,
                                  String body,
                                  String attachment) throws MessagingException;
}
