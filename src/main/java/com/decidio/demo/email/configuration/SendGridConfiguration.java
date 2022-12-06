package com.decidio.demo.email.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "send-grid")
@Data
public class SendGridConfiguration {
    private String endpoint;
    private String apiKey;
    private String fromAddress;
    private String fromName;
    private String content;
    private String subject;
}
