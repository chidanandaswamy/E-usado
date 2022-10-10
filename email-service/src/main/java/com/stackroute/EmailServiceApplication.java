package com.stackroute;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class EmailServiceApplication {

//	@Autowired
//	private EmailService service;

//	@PostMapping("/sendingEmail")
//	public MailResponse sendEmail(@RequestBody MailRequest request) {
//		Map<String, Object> model = new HashMap<>();
//		model.put("name", request.getName());
//		model.put("location", "Banglore, India");
//
//		return service.sendEmail(request, model);
//	}
//
//	public static void main(String[] args) {
//		SpringApplication.run(EmailFreemarkerApplication.class, args);
//	}
}
