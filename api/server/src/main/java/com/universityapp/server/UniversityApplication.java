package com.universityapp.server;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.universityapp")
@EnableAspectJAutoProxy
@EnableJpaRepositories
@EntityScan(basePackages = "com.universityapp.common.entities")
public class UniversityApplication {

    public static void main(String[] args) {
        SpringApplication.run(UniversityApplication.class, args);
    }
}
