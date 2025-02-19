package com.universityapp.common.config;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import com.universityapp.common.properties.LoggerProperties;
import java.util.Map;
import net.logstash.logback.encoder.LogstashEncoder;
import org.slf4j.LoggerFactory;
import org.springframework.boot.logging.LogLevel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggerConfig {

    private final LoggerProperties loggerProperties;

    public LoggerConfig(LoggerProperties loggerProperties) {
        this.loggerProperties = loggerProperties;
    }

    @Bean
    public Logger configureLogger() {
        // Fetch the root logger
        Logger rootLogger = (Logger) LoggerFactory.getLogger(
            org.slf4j.Logger.ROOT_LOGGER_NAME
        );

        // Set the root logger level dynamically from properties
        Map<String, LogLevel> levels = loggerProperties.getLevel();
        if (levels.containsKey("root")) {
            rootLogger.setLevel(
                Level.toLevel(levels.get("root").toString(), Level.INFO)
            );
        }

        // Create and configure the console appender
        ConsoleAppender<ILoggingEvent> consoleAppender =
            new ConsoleAppender<>();
        consoleAppender.setContext(rootLogger.getLoggerContext());
        if (loggerProperties.isJson()) {
            // Use JSON Logging
            LogstashEncoder jsonEncoder = new LogstashEncoder();
            jsonEncoder.setIncludeCallerData(true);
            consoleAppender.setEncoder(jsonEncoder);
        } else {
            // Use Pattern Logging
            PatternLayoutEncoder patternEncoder = new PatternLayoutEncoder();
            patternEncoder.setPattern(loggerProperties.getPattern());
            patternEncoder.setContext(rootLogger.getLoggerContext());
            patternEncoder.start();
            consoleAppender.setEncoder(patternEncoder);
        }

        // Add the appender and start it
        consoleAppender.start();
        rootLogger.detachAndStopAllAppenders(); // Detach any previously added appenders
        rootLogger.addAppender(consoleAppender); // Add new appender
        return rootLogger; // Return the root logger with the configured appender
    }
}
