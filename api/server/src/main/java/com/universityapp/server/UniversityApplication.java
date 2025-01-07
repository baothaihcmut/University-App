package com.universityapp.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import com.universityapp.users.dtos.projection.UserDTO;
import com.universityapp.users.repositories.UserRepository;

@SpringBootApplication(scanBasePackages = "com.universityapp")
@EntityScan(basePackages = {
        "com.universityapp.users.entities"
})
public class UniversityApplication implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(UniversityApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // this.userRepository.insertUser("baothai@gmail.com");
        List<UserDTO> res = this.userRepository.findAllUser();
        System.out.println(res.getFirst().getEmail());
    }

}