package com.universityapp.auth.controllers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.universityapp.auth.interactors.AuthInteractor;
import com.universityapp.auth.presenters.input.LoginInput;
import com.universityapp.common.response.AppResponse;

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

   
}
