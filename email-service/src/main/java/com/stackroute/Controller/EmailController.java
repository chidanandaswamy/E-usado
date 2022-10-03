//package com.stackroute.Controller;
//
//import com.stackroute.service.EmailService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@Controller
//public class EmailController {
//
//    @Autowired
//    private EmailService emailService;
//
//    @RequestMapping("/sendEmail")
//    public String sendEmail(){
//        return "Email";
//    }
//
//    @RequestMapping("/composeEmail")
//    public String composeEmail(@RequestParam("to") String to, @RequestParam("subject") String subject,
//                               @RequestParam("body") String body, ModelMap model  ){
//        emailService.sendSimpleMessage(to,subject,body);
//        model.addAttribute("msg", "Email sent");
//        return "Email";
//    }
//
//
//}
