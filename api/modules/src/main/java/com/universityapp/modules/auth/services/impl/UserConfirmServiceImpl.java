package com.universityapp.modules.auth.services.impl;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.universityapp.common.email.EmailService;
import com.universityapp.common.redis.RedisService;
import com.universityapp.modules.auth.services.UserConfirmService;
import com.universityapp.modules.users.entities.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserConfirmServiceImpl implements UserConfirmService {
    private final RedisService redisService;
    private final EmailService emailService;

    @Override
    public String storeUser(User user) throws Exception {
        // geneate code
        String code = UUID.randomUUID().toString();
        redisService.setObject(code, (Object) user, 30, TimeUnit.MINUTES);
        return code;
    }

    @Override
    public void sendEmail(String code, String email, String firstName, String lastName) throws Exception {
        String url = String.format("localhost:8080/api/v1/confirm?code=%s", code);
        emailService.sendEmail(email, "Email verification", "email-verification-template",
                Map.of(
                        "firstName", firstName,
                        "lastName", lastName,
                        "verficationUrl", url));
    }

}
