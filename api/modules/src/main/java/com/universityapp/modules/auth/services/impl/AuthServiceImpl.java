package com.universityapp.modules.auth.services.impl;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.universityapp.common.enums.Role;
import com.universityapp.common.models.UserContext;
import com.universityapp.common.properties.JwtProperties;
import com.universityapp.modules.auth.services.AuthService;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtProperties jwtProperties;

    private final PasswordEncoder passwordEncoder;

    public String genAccessToken(UUID userId, Role role) throws Exception {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
            .subject(userId.toString())
            .expirationTime(
                new Date(
                    Instant.now()
                        .plus(
                            jwtProperties.getAccessToken().getExpiry(),
                            ChronoUnit.HOURS
                        )
                        .toEpochMilli()
                )
            )
            .claim("scope", role.getValue())
            .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        jwsObject.sign(
            new MACSigner(jwtProperties.getAccessToken().getSecret())
        );
        return jwsObject.serialize();
    }

    public String genRefreshToken(UUID userId) throws Exception {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
            .subject(userId.toString())
            .expirationTime(
                new Date(
                    Instant.now()
                        .plus(
                            jwtProperties.getRefreshToken().getExpiry(),
                            ChronoUnit.HOURS
                        )
                        .toEpochMilli()
                )
            )
            .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        jwsObject.sign(
            new MACSigner(jwtProperties.getRefreshToken().getSecret())
        );
        return jwsObject.serialize();
    }

    public UserContext getUserContext() {
        SecurityContext context = SecurityContextHolder.getContext();
        String id = context.getAuthentication().getName();
        Role[] roles = context
            .getAuthentication()
            .getAuthorities()
            .stream()
            .map(auth -> {
                try {
                    return Role.valueOf(
                        auth.getAuthority().toUpperCase().substring(5)
                    );
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Invalid role");
                }
            })
            .filter(Objects::nonNull)
            .toArray(Role[]::new);
        return new UserContext(id,roles);
    }

    public String encodePassword(String password) {
        return this.passwordEncoder.encode(password);
    }

    public boolean checkPasswordMatch(String password, String hashPassword) {
        return this.passwordEncoder.matches(password, hashPassword);
    }
}
