package com.universityapp.common.properties;

import java.util.Map;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "logging")
@Data
public class LoggerProperties {

    private Map<String, LogLevel> level;
    private boolean json;
    private String pattern;
}
