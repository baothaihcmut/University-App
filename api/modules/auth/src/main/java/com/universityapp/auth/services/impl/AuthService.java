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
import com.universityapp.auth.dtos.internal.UserContext;
import com.universityapp.auth.dtos.internal.UserDTO;
import com.universityapp.auth.dtos.request.LoginRequestDTO;
import com.universityapp.auth.dtos.request.SignUpRequestDTO;
import com.universityapp.auth.dtos.response.LoginResponseDTO;
import com.universityapp.auth.dtos.response.TokenResponse;
import com.universityapp.auth.services.IUserAuthService;
import com.universityapp.common.enums.Role;
import com.universityapp.common.exception.AppException;
import com.universityapp.common.exception.ErrorCode;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {
    @Value("${jwt.access_token.sign_key}")
    private String accessTokenSignKey;

    @Value("${jwt.access_token.expiration}")
    private Integer accessTokenExpiration;

    @Value("${jwt.refresh_token.sign_key}")
    private String refreshTokenSignKey;

    @NonFinal
    @Value("${jwt.refresh_token.expiration}")
    private Integer refreshTokenExpiration;

    private final IUserAuthService userService;
    private final PasswordEncoder passwordEncoder;

    private String genAccessToken(UserDTO user) throws Exception {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUserId().toString())
                .expirationTime(new Date(Instant.now().plus(accessTokenExpiration, ChronoUnit.HOURS).toEpochMilli()))
                .claim("scope", user.getRole().getValue())
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        jwsObject.sign(new MACSigner(accessTokenSignKey));
        return jwsObject.serialize();
    }

    private String genRefreshToken(UserDTO user) throws Exception {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUserId().toString())
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

    @Transactional
    public LoginResponseDTO logIn(LoginRequestDTO requestDTO) throws Exception {
        UserDTO userDTO = this.userService.findUserByEmail(requestDTO.getEmail())
                .orElseThrow(() -> new AppException(ErrorCode.BAD_CREDENTIALS_EXCEPTION));

        // check if password match
        if (!this.passwordEncoder.matches(requestDTO.getPassword(), userDTO.getPassword())) {
            throw new AppException(ErrorCode.BAD_CREDENTIALS_EXCEPTION);
        }
        // generate token
        String accessToken = this.genAccessToken(userDTO);
        String refreshToken = this.genRefreshToken(userDTO);
        // update refresh token
        this.userService.updateCurrentRefreshToken(userDTO.getUserId(), refreshToken);
        return LoginResponseDTO.builder().isActive(userDTO.getIsActive())
                .role(userDTO.getRole())
                .token(
                        TokenResponse.builder().accessToken(accessToken)
                                .refreshToken(refreshToken)
                                .build())
                .build();
    }

    @Transactional
    public LoginResponseDTO signUp(SignUpRequestDTO dto) throws Exception {
        // check if email and phone number exist
        try {
            UserDTO userDTO = this.userService.findUserByEmail(dto.getEmail()).orElseGet(null);
            if (userDTO != null) {
                throw new AppException(ErrorCode.EMAIL_EXIST);
            }
        } catch (AppException e) {
            // if e not equal to user not found exception throw
            if (!e.getMessage().equals(ErrorCode.USER_NOT_FOUND.getMessage())) {
                throw e;
            }
        }
        try {
            UserDTO userDTO = this.userService.findUserByPhoneNumber(dto.getPhoneNumber()).orElseGet(null);
            if (userDTO != null) {
                throw new AppException(ErrorCode.PHONE_NUMBER_EXIST);
            }
        } catch (AppException e) {
            // if e not equal to user not found exception throw
            if (!e.getMessage().equals(ErrorCode.USER_NOT_FOUND.getMessage())) {
                throw e;
            }
        }

        // encrypt password
        dto.setPassword(this.passwordEncoder.encode(dto.getPassword()));
        // create new user
        UserDTO userDTO = this.userService.createUser(dto);
        // generate token for new user
        String accessToken = this.genAccessToken(userDTO);
        String refreshToken = this.genRefreshToken(userDTO);
        // update new accessToken
        this.userService.updateCurrentRefreshToken(userDTO.getUserId(), refreshToken);
        return LoginResponseDTO.builder().isActive(userDTO.getIsActive())
                .role(userDTO.getRole())
                .token(
                        TokenResponse.builder().accessToken(accessToken)
                                .refreshToken(refreshToken)
                                .build())
                .build();
    }

}
