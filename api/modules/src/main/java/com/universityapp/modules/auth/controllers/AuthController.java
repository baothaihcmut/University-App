package com.universityapp.modules.auth.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.universityapp.common.redis.RedisService;
import com.universityapp.common.response.AppResponse;
import com.universityapp.modules.auth.interactors.AuthInteractor;
import com.universityapp.modules.auth.presenters.input.LoginInput;
import com.universityapp.modules.auth.presenters.input.SignUpInput;
import com.universityapp.modules.users.entities.User;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthInteractor authInteractor;
    private final RedisService redisService;

    @PostMapping("/log-in")
    public ResponseEntity<AppResponse> logIn(@RequestBody @Valid LoginInput dto) throws Exception {
        return AppResponse.initResponse(HttpStatus.CREATED, true, "Login success", this.authInteractor.logIn(dto));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<AppResponse> signUp(
            @RequestBody @Valid SignUpInput input) throws Exception {
        return AppResponse.initResponse(
                HttpStatus.CREATED, true, "Sign up success",
                authInteractor.signUp(input));
    }

    @PostMapping("/test")
    public ResponseEntity<AppResponse> test() throws Exception {
        User user = this.redisService.getValue("640e9bad-4af1-4069-b696-7d6707f4b087", User.class);
        return AppResponse.initResponse(HttpStatus.OK, true, "user", user);
    }

}
