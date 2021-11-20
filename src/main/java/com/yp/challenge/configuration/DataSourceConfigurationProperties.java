package com.yp.challenge.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "datasource")
public class DataSourceConfigurationProperties {
    private String url;
    private String username;
    private String password;
    private String driverClassName;
}
