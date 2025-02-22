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
        //store email in pending
        redisService.setString(String.format("email_pending_verification:%s", user.getEmail()), "1", 30, TimeUnit.MINUTES);
        // geneate code
        String code = UUID.randomUUID().toString();
        redisService.setObject(String.format("email_verfication_code:%s", code), user, 30, TimeUnit.MINUTES);
        return code;
    }

    @Override
    public void sendEmail(String code, String email, String firstName, String lastName) throws Exception {
        String url = String.format("http://localhost:8080/api/v1/confirm?code=%s", code);
        emailService.sendEmail(email, "Email verification", "email-verification-template",
                Map.of(
                        "firstName", firstName,
                        "lastName", lastName,
                        "verificationUrl", url));
    }

    @Override
    public boolean isUserPendingVerification(String email) throws Exception {
        return redisService.getValueString(String.format("email_pending_verification:%s", email)) != null ;
    }

    @Override
    public User getUser(String code) throws Exception {
        return redisService.getValueObject(String.format("email_verfication_code:%s", code), User.class);
    }

    

}
