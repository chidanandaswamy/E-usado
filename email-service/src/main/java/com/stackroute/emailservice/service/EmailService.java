package com.stackroute.emailservice.service;


import com.stackroute.emailservice.dto.MailRequest;
import com.stackroute.emailservice.dto.MailResponse;
import com.stackroute.emailservice.model.OrderService;
import com.stackroute.emailservice.model.SlotBooking;
import com.stackroute.emailservice.model.UserService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender sender;

    @Autowired
    private Configuration config;


    public MailResponse OrderConformationsendEmail(OrderService request, Map<String, Object> model) {

        MailResponse response = new MailResponse();
        MimeMessage message = sender.createMimeMessage();
        try {
            // set mediaType
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            // add attachment
            helper.addAttachment("logo.png", new ClassPathResource("logo.png"));
            //ClassPathResource pdf = new ClassPathResource("static/invoice.pdf");

            Template t = config.getTemplate("email-template.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

            helper.setTo(request.getBuyerEmail());
            helper.setText(html, true);
            helper.setSubject("Order Confirmation Email");
            helper.setFrom("eusado3@gmail.com");

            sender.send(message);

            response.setMessage("mail send to : " + request.getBuyerEmail());
            response.setStatus(Boolean.TRUE);

        } catch (MessagingException | IOException | TemplateException e) {
            response.setMessage("Mail Sending failure : " + e.getMessage());
            response.setStatus(Boolean.FALSE);
        }

        return response;
    }

    public MailResponse sendEmailThankyou(UserService request1, Map<String, Object> model) {

        MailResponse response = new MailResponse();
        MimeMessage message = sender.createMimeMessage();
        try {
            // set mediaType
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            // add attachment
            helper.addAttachment("logo.png", new ClassPathResource("logo.png"));
            //ClassPathResource pdf = new ClassPathResource("static/invoice.pdf");

            Template t = config.getTemplate("email-ThankYouRegistration.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

            helper.setTo(request1.getEmail());
            helper.setText(html, true);
            helper.setSubject("Thank You for Registration");
            helper.setFrom("eusado3@gmail.com");

	            sender.send(message);

            response.setMessage("mail send to : " + request1.getEmail());
            response.setStatus(Boolean.TRUE);

        } catch (MessagingException | IOException | TemplateException e) {
            response.setMessage("Mail Sending failure : " + e.getMessage());
            response.setStatus(Boolean.FALSE);
        }

        return response;
    }

    public MailResponse sendEmailSlotBooking(SlotBooking request1, Map<String, Object> model) {

        MailResponse response = new MailResponse();
        MimeMessage message = sender.createMimeMessage();
        try {
            // set mediaType
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            // add attachment
            helper.addAttachment("slotBookingImage.webp", new ClassPathResource("slotBookingImage.webp"));
            //ClassPathResource pdf = new ClassPathResource("static/invoice.pdf");

            Template t = config.getTemplate("email-slot.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

            helper.setTo(request1.getBuyerEmailId());
            helper.setText(html, true);
            helper.setSubject("Slot Booked Successfully");
            helper.setFrom("eusado3@gmail.com");

            sender.send(message);

            response.setMessage("mail send to : " + request1.getBuyerEmailId());
            response.setStatus(Boolean.TRUE);

        } catch (MessagingException | IOException | TemplateException e) {
            response.setMessage("Mail Sending failure : " + e.getMessage());
            response.setStatus(Boolean.FALSE);
        }

        return response;
    }

    public MailResponse sendEmailProductAdded(MailRequest request1, Map<String, Object> model) {

        MailResponse response = new MailResponse();
        MimeMessage message = sender.createMimeMessage();
        try {
            // set mediaType
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            // add attachment
            helper.addAttachment("slotBookingImage.webp", new ClassPathResource("slotBookingImage.webp"));
            //ClassPathResource pdf = new ClassPathResource("static/invoice.pdf");

            Template t = config.getTemplate("email-ProductAdded.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

            helper.setTo(request1.getProductOwnerEmail());
            helper.setText(html, true);
            helper.setSubject("Product added successfully");
            helper.setFrom("eusado3@gmail.com");

            sender.send(message);

            response.setMessage("mail send to : " + request1.getProductOwnerEmail());
            response.setStatus(Boolean.TRUE);

        } catch (MessagingException | IOException | TemplateException e) {
            response.setMessage("Mail Sending failure : " + e.getMessage());
            response.setStatus(Boolean.FALSE);
        }

        return response;
    }




}

