package com.universityapp.auth.controllers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.universityapp.auth.dtos.request.LoginRequestDTO;
import com.universityapp.auth.dtos.request.SignUpRequestDTO;
import com.universityapp.auth.services.IAuthService;
import com.universityapp.common.response.AppResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final IAuthService authService;

    @PostMapping("/log-in")
    public ResponseEntity<AppResponse> logIn(@RequestBody @Valid LoginRequestDTO dto) throws Exception {
        return AppResponse.initResponse(HttpStatus.CREATED, true, "Login success", this.authService.logIn(dto));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<AppResponse> signUp(@RequestBody @Valid SignUpRequestDTO dto) throws Exception {
        return AppResponse.initResponse(HttpStatus.CREATED, true, "Sign up success", this.authService.signUp(dto));
    }

    @PostMapping("/admin/log-in")
    public ResponseEntity<AppResponse> adminLogin(@RequestBody @Valid LoginRequestDTO dto) throws Exception {
        return AppResponse.initResponse(HttpStatus.CREATED, true, "Log in success", this.authService.adminLogin(dto));
    }
}
