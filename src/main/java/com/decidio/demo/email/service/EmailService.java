package com.decidio.demo.email.service;

import com.decidio.demo.email.configuration.SendGridConfiguration;
import com.sendgrid.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class EmailService {
    private final SendGridConfiguration sendGridConfiguration;

    private Email getFrom() {
        return new Email(sendGridConfiguration.getFromAddress(), sendGridConfiguration.getFromName());
    }

    public Response sendEmail(Email to) throws IOException {
        Content content = new Content("text/plain", sendGridConfiguration.getContent().replace("{name}",to.getName()));
        Mail mail = new Mail(getFrom(), sendGridConfiguration.getSubject(), to, content);

        SendGrid sg = new SendGrid(sendGridConfiguration.getApiKey());
        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint(sendGridConfiguration.getEndpoint());
        request.setBody(mail.build());
        return sg.api(request);
    }
}
