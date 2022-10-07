package com.stackroute;

import com.stackroute.service.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import javax.mail.MessagingException;

@SpringBootApplication
public class EmailServiceApplication {
@Autowired
private EmailServiceImpl service;
	public static void main(String[] args) {
		SpringApplication.run(EmailServiceApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void triggerMail() throws MessagingException {
		service.sendMail("chidanandaswamy356@gmail.com", "E-usado order conformation mail" ,"Thank for Ordering Using E-usado" ,"/home/chidananda/Downloads/windows-11-landscape-scenery-sunrise-stock-day-light-3840x2400-5661.jpg");
	}
}
