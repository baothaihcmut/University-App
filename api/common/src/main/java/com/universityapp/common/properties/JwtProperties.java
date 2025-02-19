package com.universityapp.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "jwt")
@Component
@Data
public class JwtProperties {

    @Data
    public static class TokenProperty {

        private String secret;
        private Integer expiry;
    }

    private TokenProperty accessToken;
    private TokenProperty refreshToken;
}
