package com.universityapp.auth.services.impl;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.universityapp.auth.presenters.internal.UserContext;
import com.universityapp.auth.services.AuthService;
import com.universityapp.common.enums.Role;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService  {
    @Value("${jwt.access_token.sign_key}")
    private String accessTokenSignKey;

    @Value("${jwt.access_token.expiration}")
    private Integer accessTokenExpiration;

    @Value("${jwt.refresh_token.sign_key}")
    private String refreshTokenSignKey;

    @Value("${jwt.refresh_token.expiration}")
    private Integer refreshTokenExpiration;

    private final PasswordEncoder passwordEncoder;


    public String genAccessToken(UUID userId, Role role) throws Exception {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(userId.toString())
                .expirationTime(new Date(Instant.now().plus(accessTokenExpiration, ChronoUnit.HOURS).toEpochMilli()))
                .claim("scope", role.getValue())
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        jwsObject.sign(new MACSigner(accessTokenSignKey));
        return jwsObject.serialize();
    }

    public String genRefreshToken(UUID userId) throws Exception {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(userId.toString())
                .expirationTime(new Date(Instant.now().plus(refreshTokenExpiration, ChronoUnit.HOURS).toEpochMilli()))
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        jwsObject.sign(new MACSigner(refreshTokenSignKey));
        return jwsObject.serialize();
    }

    public UserContext getUserContext() {
        SecurityContext context = SecurityContextHolder.getContext();
        String id = context.getAuthentication().getName();
        Role[] roles = context.getAuthentication().getAuthorities().stream()
                .map(auth -> {
                    try {
                        return Role.valueOf(auth.getAuthority().toUpperCase().substring(5));
                    } catch (IllegalArgumentException e) {
                        throw new IllegalArgumentException("Invalid role");
                    }
                })
                .filter(Objects::nonNull)
                .toArray(Role[]::new);
        return new UserContext(UUID.fromString(id), roles);
    }

    public String encodePassword(String password) {
        return this.passwordEncoder.encode(password);
    }

    public boolean checkPasswordMatch(String password,String hashPassword){
        return this.passwordEncoder.matches(password, hashPassword);
    }
}
