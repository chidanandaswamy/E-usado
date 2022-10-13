package com.stackroute;

import com.stackroute.dto.MailRequest;
import com.stackroute.dto.MailResponse;
import com.stackroute.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
@EnableEurekaClient
public class EmailServiceApplication {

	@Autowired
	private EmailService service;

	@PostMapping("/EmailService/OrderConformation")
	public MailResponse sendEmail(@RequestBody MailRequest request) {
		Map<String, Object> model = new HashMap<>();
		model.put("name", request.getName());

		model.put("location", "Banglore, India");

		return service.sendEmail(request, model);
	}


	@PostMapping("/EmailService/Thankyou")
	public MailResponse sendEmailThank(@RequestBody MailRequest request1) {
		Map<String, Object> model = new HashMap<>();
		model.put("name", request1.getName());
		model.put("location", "Banglore, India");

		return service.sendEmailThankyou(request1, model);
	}


	public static void main(String[] args) {
		SpringApplication.run(EmailServiceApplication.class, args);
	}
}
