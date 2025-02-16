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
import com.universityapp.admin.dtos.internal.AdminDTO;
import com.universityapp.auth.dtos.internal.CreateUserDTO;
import com.universityapp.auth.dtos.internal.UserContext;
import com.universityapp.auth.dtos.internal.UserDTO;
import com.universityapp.auth.dtos.request.LoginRequestDTO;
import com.universityapp.auth.dtos.request.SignUpRequestDTO;
import com.universityapp.auth.dtos.response.LoginResponseDTO;
import com.universityapp.auth.dtos.response.TokenResponse;
import com.universityapp.auth.mappers.UserMapper;
import com.universityapp.auth.services.IAdminAuthService;
import com.universityapp.auth.services.IAuthService;
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
public class AuthService implements IAuthService {
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
    private final IAdminAuthService adminService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    private String genAccessToken(UUID userId, Role role) throws Exception {
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

    private String genRefreshToken(UUID userId) throws Exception {
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

    @Transactional
    public LoginResponseDTO logIn(LoginRequestDTO requestDTO) throws Exception {
        UserDTO userDTO = this.userService.findUserByEmail(requestDTO.getEmail())
                .orElseThrow(() -> new AppException(ErrorCode.BAD_CREDENTIALS_EXCEPTION));

        // check if password match
        if (!this.passwordEncoder.matches(requestDTO.getPassword(), userDTO.getPassword())) {
            throw new AppException(ErrorCode.BAD_CREDENTIALS_EXCEPTION);
        }
        // generate token
        String accessToken = this.genAccessToken(userDTO.getUserId(), userDTO.getRole());
        String refreshToken = this.genRefreshToken(userDTO.getUserId());
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
        CreateUserDTO createUserDTO = this.userMapper.toCreateUserDTO(dto);
        createUserDTO.setUserId(UUID.randomUUID());
        createUserDTO.setIsActive(true);
        createUserDTO.setPassword(this.passwordEncoder.encode(dto.getPassword()));
        // generate token for new user
        String accessToken = this.genAccessToken(createUserDTO.getUserId(), createUserDTO.getRole());
        String refreshToken = this.genRefreshToken(createUserDTO.getUserId());
        createUserDTO.setCurrentRefreshToken(refreshToken);
        this.userService.createUser(createUserDTO);
        // update new accessToken

        return LoginResponseDTO.builder().isActive(createUserDTO.getIsActive())
                .role(createUserDTO.getRole())
                .token(
                        TokenResponse.builder().accessToken(accessToken)
                                .refreshToken(refreshToken)
                                .build())
                .build();
    }

    @Override
    @Transactional
    public LoginResponseDTO adminLogin(LoginRequestDTO requestDTO) throws Exception {
        // find admin by dto
        AdminDTO adminDTO = this.adminService.findAdminByEmail(requestDTO.getEmail())
                .orElseThrow(() -> new AppException(ErrorCode.BAD_CREDENTIALS_EXCEPTION));
        // check if password match
        if (!this.passwordEncoder.matches(requestDTO.getPassword(), adminDTO.getPassword())) {
            System.out.println(adminDTO.getPassword());
            throw new AppException(ErrorCode.BAD_CREDENTIALS_EXCEPTION);
        }
        // gen token
        String accessToken = this.genAccessToken(adminDTO.getAdminId(), Role.ADMIN);
        String refreshToken = this.genRefreshToken(adminDTO.getAdminId());
        // update refresh token
        this.adminService.updateAdminRefreshToken(adminDTO.getAdminId(), refreshToken);
        return LoginResponseDTO.builder().isActive(true)
                .role(Role.ADMIN)
                .token(
                        TokenResponse.builder().accessToken(accessToken)
                                .refreshToken(refreshToken)
                                .build())
                .build();
    }

}
