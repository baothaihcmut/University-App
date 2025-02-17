package com.universityapp.auth.interactors.impl;

import org.springframework.stereotype.Service;

import com.universityapp.auth.presenters.input.LoginInput;
import com.universityapp.auth.presenters.output.LoginOutput;
import com.universityapp.auth.presenters.output.TokenResponse;
import com.universityapp.auth.services.AuthService;
import com.universityapp.common.entities.User;
import com.universityapp.common.exception.AppException;
import com.universityapp.common.exception.ErrorCode;
import com.universityapp.users.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthInteractorImpl {
    private final UserRepository userRepository;
    private final AuthService authService;


    public LoginOutput logIn(LoginInput input)  throws Exception{
        //find user by email 
        User user = this.userRepository
                        .findUserByEmail(input.getEmail()).orElseThrow(()->new AppException(ErrorCode.BAD_CREDENTIALS_EXCEPTION));
        //check password
        if (this.authService.checkPasswordMatch(input.getPassword(), user.getPassword())) {
            throw new AppException(ErrorCode.BAD_CREDENTIALS_EXCEPTION);
        }
        //generate token
        String accessToken = this.authService.genAccessToken(user.getUserId(), user.getRole());
        String refreshToken = this.authService.genRefreshToken(user.getUserId());
        return LoginOutput.builder()
                          .isActive(user.getIsActive())
                          .role(user.getRole())
                          .token(TokenResponse.builder()
                                              .accessToken(accessToken)
                                              .refreshToken(refreshToken)
                                              .build())
                          .build();
    }
}
