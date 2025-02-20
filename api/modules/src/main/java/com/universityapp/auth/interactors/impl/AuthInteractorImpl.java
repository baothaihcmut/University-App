package com.universityapp.auth.interactors.impl;

import ch.qos.logback.classic.Logger;
import com.universityapp.auth.interactors.AuthInteractor;
import com.universityapp.auth.mappers.UserMapper;
import com.universityapp.auth.presenters.input.LoginInput;
import com.universityapp.auth.presenters.input.SignUpInput;
import com.universityapp.auth.presenters.output.LoginOutput;
import com.universityapp.auth.presenters.output.TokenResponse;
import com.universityapp.auth.services.AuthService;
import com.universityapp.common.exception.AppException;
import com.universityapp.common.exception.ErrorCode;
import com.universityapp.common.logger.LoggerUtil;
import com.universityapp.users.entities.User;
import com.universityapp.users.repositories.UserRepository;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthInteractorImpl implements AuthInteractor {

    private final UserRepository userRepository;
    private final AuthService authService;
    private final UserMapper userMapper;
    private final Logger logger = (Logger) LoggerFactory.getLogger(
        AuthInteractorImpl.class
    );

    public LoginOutput logIn(LoginInput input) throws Exception {
        try {
            // Find user by email
            User user =
                this.userRepository.findUserByEmail(
                        input.getEmail()
                    ).orElseThrow(() -> {
                        // Log the failure if email does not exist
                        LoggerUtil.warn(
                            logger,
                            "user log in fail",
                            Map.of(
                                "detail",
                                "email not exist",
                                "email",
                                input.getEmail()
                            )
                        );
                        throw new AppException(
                            ErrorCode.BAD_CREDENTIALS_EXCEPTION
                        );
                    });
            // Check password match
            
            if (
                this.authService.checkPasswordMatch(
                        input.getPassword(),
                        user.getPassword()
                    )
            ) {
                // Log the failure if password is incorrect
                LoggerUtil.warn(
                    logger,
                    "user log in fail",
                    Map.of(
                        "detail",
                        "wrong password",
                        "email",
                        input.getEmail()
                    )
                );
                throw new AppException(ErrorCode.BAD_CREDENTIALS_EXCEPTION);
            }

            // Generate token for the user
            String accessToken =
                this.authService.genAccessToken(
                        user.getUserId(),
                        user.getRole()
                    );
            String refreshToken =
                this.authService.genRefreshToken(user.getUserId());

            // Log success
            LoggerUtil.warn(
                logger,
                "user log in success",
                Map.of("email", input.getEmail())
            );

            // Return login output with token response
            return LoginOutput.builder()
                .isActive(user.getIsActive())
                .role(user.getRole())
                .token(
                    TokenResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build()
                )
                .build();
        } finally {
            // Always clear MDC context after processing the request
            MDC.clear();
        }
    }

    public LoginOutput SignUp(SignUpInput input) throws Exception {
        //check if email exist
        User user =
            this.userRepository.findUserByEmail(input.getEmail()).orElse(null);
        if (user == null) {
            throw new AppException(ErrorCode.EMAIL_EXIST);
        }
        //create new user in db
        User newUser = this.userMapper.toUser(input);
        //generate id for user
        user.setUserId(UUID.randomUUID());
        //set user not active
        user.setIsActive(false);
        //encode password
        newUser.setPassword(
            this.authService.encodePassword(newUser.getPassword())
        );
        //genrate accress and refresh token
        String accessToken =
            this.authService.genAccessToken(
                    newUser.getUserId(),
                    newUser.getRole()
                );
        String refreshToken =
            this.authService.genRefreshToken(newUser.getUserId());
        //set refresh token
        user.setCurrentRefreshToken(refreshToken);
        newUser = this.userRepository.save(newUser);
        
        return LoginOutput.builder()
            .isActive(newUser.getIsActive())
            .role(newUser.getRole())
            .token(
                TokenResponse.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build()
            )
            .build();
    }
}
