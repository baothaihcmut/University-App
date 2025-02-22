package com.universityapp.server;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

import lombok.RequiredArgsConstructor;


@SpringBootApplication(scanBasePackages = "com.universityapp")
@ConfigurationPropertiesScan(basePackages = {
    "com.universityapp.common.properties"
})
@EnableAspectJAutoProxy
@RequiredArgsConstructor
@EnableAsync
public class UniversityApplication{
    public static void main(String[] args) {
        SpringApplication.run(UniversityApplication.class, args);
    }

   
}
