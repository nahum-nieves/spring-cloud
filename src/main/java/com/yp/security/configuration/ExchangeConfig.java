package com.yp.security.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "application.exchange")
public class ExchangeConfig {
    private String url;
    private String accessKey;
}
