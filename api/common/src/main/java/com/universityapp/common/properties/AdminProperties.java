package com.universityapp.common.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@ConfigurationProperties(prefix = "admin")
@Component
@Data
public class AdminProperties {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
}
