package com.universityapp.modules.auth.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.universityapp.common.response.AppResponse;
import com.universityapp.modules.auth.interactors.AuthInteractor;
import com.universityapp.modules.auth.presenters.input.ConfirmSignUpInput;
import com.universityapp.modules.auth.presenters.input.LoginInput;
import com.universityapp.modules.auth.presenters.input.SignUpInput;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthInteractor authInteractor;

    @PostMapping("/log-in")
    public ResponseEntity<AppResponse> logIn(@RequestBody @Valid LoginInput dto) throws Exception {
        return AppResponse.initResponse(HttpStatus.CREATED, true, "Login success", this.authInteractor.logIn(dto));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<AppResponse> signUp(
            @RequestBody @Valid SignUpInput input) throws Exception {
        authInteractor.signUp(input);
        return AppResponse.initResponse(
                HttpStatus.CREATED, 
                true, 
                "Sign up success",
                null);
    }

    @PostMapping("/confirm")
    public ResponseEntity<AppResponse> confirm(
        @RequestBody @Valid ConfirmSignUpInput input
    ) throws Exception{
        return AppResponse.initResponse(
            HttpStatus.CREATED, 
            true, 
            "Confirm sign up success", 
            this.authInteractor.confirmSignUp(input));
    }
    
}
