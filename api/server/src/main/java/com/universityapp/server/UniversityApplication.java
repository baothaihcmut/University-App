package com.universityapp.server;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication(scanBasePackages = "com.universityapp")
@EnableAspectJAutoProxy
@EntityScan(basePackages = "com.universityapp.common.entities")
public class UniversityApplication {

    public static void main(String[] args) {
        ((Logger) LoggerFactory.getLogger(UniversityApplication.class)).info(
                "Startedddd"
            );
        SpringApplication.run(UniversityApplication.class, args);
    }
}
