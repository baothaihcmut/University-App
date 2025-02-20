package com.universityapp.server.initialize;

import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.universityapp.admin.entities.Admin;
import com.universityapp.admin.repositories.AdminRepository;
import com.universityapp.auth.services.AuthService;
import com.universityapp.common.properties.AdminProperties;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AdminInitialize implements CommandLineRunner{
    private final AdminRepository adminRepository;
    private final AdminProperties adminProperties;
    private final AuthService authService;
    @Override
    public void run(String... args) throws Exception {
        Admin existAdmin = this.adminRepository.findAdminByEmail(adminProperties.getEmail()).orElseGet(null);
        if(existAdmin!=null) {
            return;
        }
        Admin admin = Admin.builder()
                           .id(UUID.randomUUID())
                           .email(adminProperties.getEmail())
                           .password(this.authService.encodePassword(adminProperties.getPassword()))
                           .firstName(adminProperties.getFirstName())
                           .lastName(adminProperties.getLastName())
                           .phoneNumber(adminProperties.getPhoneNumber())
                           .build();
        adminRepository.save(admin);
    }
    
}
