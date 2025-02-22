package com.universityapp.modules.auth.interactors.impl;

import java.util.Map;

import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.universityapp.common.exception.AppException;
import com.universityapp.common.exception.ErrorCode;
import com.universityapp.common.logger.LoggerUtil;
import com.universityapp.modules.auth.event.events.UserRegisteredEvent;
import com.universityapp.modules.auth.interactors.AuthInteractor;
import com.universityapp.modules.auth.presenters.input.ConfirmSignUpInput;
import com.universityapp.modules.auth.presenters.input.LoginInput;
import com.universityapp.modules.auth.presenters.input.SignUpInput;
import com.universityapp.modules.auth.presenters.output.ConfirmSignUpOutput;
import com.universityapp.modules.auth.presenters.output.LoginOutput;
import com.universityapp.modules.auth.presenters.output.TokenResponse;
import com.universityapp.modules.auth.services.AuthService;
import com.universityapp.modules.auth.services.UserConfirmService;
import com.universityapp.modules.users.entities.User;
import com.universityapp.modules.users.repositories.UserRepository;

import ch.qos.logback.classic.Logger;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthInteractorImpl implements AuthInteractor {

    private final UserRepository userRepository;
    private final AuthService authService;
    private final UserConfirmService userConfirmService;
    private final ApplicationEventPublisher eventPublisher;
    private final Logger logger = (Logger) LoggerFactory.getLogger(
            AuthInteractorImpl.class);
            AuthInteractorImpl.class);

        public LoginOutput logIn(LoginInput input) throws Exception {
                try {
                // Find user by email
                User user = this.userRepository.findUserByEmailAndIsActive(
                        input.getEmail(), true).orElseThrow(() -> {
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

        public void signUp(SignUpInput input) throws Exception {
                // check if email exist
                User user = this.userRepository
                                .findUserByEmailAndIsActive(input.getEmail(),false)
                                .orElseThrow(()->new AppException(ErrorCode.ACCOUNT_NOT_IN_SYTEM));
                

                //check if user pending confirm
                if(this.userConfirmService.isUserPendingVerification(input.getEmail())) {
                throw new AppException(ErrorCode.USER_PENDING_VERFICATION_SIGN_UP);
                }

                //set user new information
                user.setFirstName(input.getFirstName());
                user.setLastName(input.getLastName());
                user.setPassword(input.getPhoneNumber());
                user.setBirthday(input.getBirthday());
                user.setBirthplace(input.getBirthplace());
                user.setAddress(input.getAddress());
                user.setPhoneNumber(input.getPhoneNumber());
                user.setSocialNetworkInfo(input.getSocialNetworkInfo());
                // encode password
                user.setPassword(
                        this.authService.encodePassword(input.getPassword()));
                //activate user
                String code = this.userConfirmService.storeUser(user);

                //publish event send mail
                UserRegisteredEvent event = new UserRegisteredEvent(user.getEmail(), code, user.getFirstName(), user.getLastName());
                eventPublisher.publishEvent(event);
        }

        @Override
        @Transactional
        public ConfirmSignUpOutput confirmSignUp(ConfirmSignUpInput input) throws Exception {
                //get user from redis
                User user = this.userConfirmService.getUser(input.getCode());
                if(user ==null) {
                throw new AppException(ErrorCode.WRONG_VERIFICATION_CODE);
                }
                //activate user
                user.setIsActive(true);
                //gen refresh token 
                String refreshToken = this.authService.genRefreshToken(user.getUserId());
                user.setCurrentRefreshToken(refreshToken);
                //store user to db
                this.userRepository.save(user);
                //gen access token
                String accessToken = this.authService.genAccessToken(user.getUserId(), user.getRole());

                return ConfirmSignUpOutput.builder()
                                        .role(user.getRole())
                                        .token(
                                                TokenResponse.builder()
                                                        .accessToken(accessToken)
                                                        .refreshToken(refreshToken)
                                                        .build()
                                        ).build();
        }


    
       

}
