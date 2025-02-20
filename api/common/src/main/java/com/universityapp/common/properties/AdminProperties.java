package com.universityapp.common.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import lombok.Data;

@ConfigurationProperties(prefix = "admin")
@ConfigurationPropertiesScan
@Data
public class AdminProperties {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
}
