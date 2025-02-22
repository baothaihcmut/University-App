package com.universityapp.server.initialize;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.universityapp.common.properties.AdminProperties;
import com.universityapp.modules.admin.entities.Admin;
import com.universityapp.modules.admin.repositories.AdminRepository;
import com.universityapp.modules.auth.services.AuthService;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AdminInitialize{
    private final AdminRepository adminRepository;
    private final AdminProperties adminProperties;
    private final AuthService authService;

    @PostConstruct
    public void createAdmin()  {
        if(this.adminRepository.findAdminByEmail(adminProperties.getEmail()).orElse(null)!=null) {
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
