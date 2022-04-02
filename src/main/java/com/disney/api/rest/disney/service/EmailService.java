package com.disney.api.rest.disney.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    private final String subject;
    private final String content;

    @Value("${sendgrid.from}")
    private String emailFrom;

    @Value("${sendgrid.api.key}")
    private String secret;

    public EmailService() {
        subject = "Welcome to my API-REST disney";
        content = "Thank you for registering 😉";
    }

    public void sendTextEmail(String emailTo) throws IOException {
        Email from = new Email(emailFrom);
        Email to = new Email(emailTo);
        Content content = new Content("text/plain", this.content);
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(secret);
        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());
        Response response = sg.api(request);
        logger.info(response.getBody());
    }
}
