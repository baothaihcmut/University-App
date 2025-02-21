package com.universityapp.modules.auth.interactors.impl;

import java.util.Map;
import java.util.UUID;

import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

import com.universityapp.common.exception.AppException;
import com.universityapp.common.exception.ErrorCode;
import com.universityapp.common.logger.LoggerUtil;
import com.universityapp.modules.auth.interactors.AuthInteractor;
import com.universityapp.modules.auth.mappers.UserMapper;
import com.universityapp.modules.auth.presenters.input.LoginInput;
import com.universityapp.modules.auth.presenters.input.SignUpInput;
import com.universityapp.modules.auth.presenters.output.LoginOutput;
import com.universityapp.modules.auth.presenters.output.TokenResponse;
import com.universityapp.modules.auth.services.AuthService;
import com.universityapp.modules.auth.services.UserConfirmService;
import com.universityapp.modules.users.entities.User;
import com.universityapp.modules.users.repositories.UserRepository;

import ch.qos.logback.classic.Logger;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthInteractorImpl implements AuthInteractor {

    private final UserRepository userRepository;
    private final AuthService authService;
    private final UserMapper userMapper;
    private final UserConfirmService userConfirmService;
    private final Logger logger = (Logger) LoggerFactory.getLogger(
            AuthInteractorImpl.class);

    public LoginOutput logIn(LoginInput input) throws Exception {
        try {
            // Find user by email
            User user = this.userRepository.findUserByEmail(
                    input.getEmail()).orElseThrow(() -> {
                        // Log the failure if email does not exist
                        LoggerUtil.warn(
                                logger,
                                "user log in fail",
                                Map.of(
                                        "detail",
                                        "email not exist",
                                        "email",
                                        input.getEmail()));
                        throw new AppException(
                                ErrorCode.BAD_CREDENTIALS_EXCEPTION);
                    });
            // Check password match

            if (this.authService.checkPasswordMatch(
                    input.getPassword(),
                    user.getPassword())) {
                // Log the failure if password is incorrect
                LoggerUtil.warn(
                        logger,
                        "user log in fail",
                        Map.of(
                                "detail",
                                "wrong password",
                                "email",
                                input.getEmail()));
                throw new AppException(ErrorCode.BAD_CREDENTIALS_EXCEPTION);
            }

            // Generate token for the user
            String accessToken = this.authService.genAccessToken(
                    user.getUserId(),
                    user.getRole());
            String refreshToken = this.authService.genRefreshToken(user.getUserId());

            // Log success
            LoggerUtil.warn(
                    logger,
                    "user log in success",
                    Map.of("email", input.getEmail()));

            // Return login output with token response
            return LoginOutput.builder()
                    .isActive(user.getIsActive())
                    .role(user.getRole())
                    .token(
                            TokenResponse.builder()
                                    .accessToken(accessToken)
                                    .refreshToken(refreshToken)
                                    .build())
                    .build();
        } finally {
            // Always clear MDC context after processing the request
            MDC.clear();
        }
    }

    public String signUp(SignUpInput input) throws Exception {
        // check if email exist
        User user = this.userRepository.findUserByEmail(input.getEmail()).orElse(null);
        if (user != null) {
            throw new AppException(ErrorCode.EMAIL_EXIST);
        }
        // create new user in db
        User newUser = this.userMapper.toUser(input);
        // generate id for user
        newUser.setUserId(UUID.randomUUID());
        // set user not active
        newUser.setIsActive(false);
        // encode password
        newUser.setPassword(
                this.authService.encodePassword(newUser.getPassword()));
        String code = this.userConfirmService.storeUser(newUser);
        this.userConfirmService.sendEmail(code, newUser.getEmail(), newUser.getFirstName(), newUser.getLastName());
        return code;
    }

}
