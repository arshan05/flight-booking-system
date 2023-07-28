package com.fbs.mail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fbs.mail.model.EmailRequest;
import com.fbs.mail.service.EmailService;

@RestController
public class EmailController {

	@Autowired
    EmailService emailService;

    @PostMapping("/sendEmail")
    public void sendEmail(@RequestBody EmailRequest emailRequest) {
        emailService.sendEmail(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getBody());
    }
}

