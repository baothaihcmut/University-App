package com.universityapp.modules.admin.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.universityapp.common.response.AppResponse;
import com.universityapp.modules.admin.interactors.AdminInteractor;
import com.universityapp.modules.admin.presenters.input.AdminLoginInput;
import com.universityapp.modules.admin.presenters.output.AdminLoginOuput;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminInteractor adminInteractor;

    @PostMapping("/log-in")
    public ResponseEntity<AppResponse> logIn(@RequestBody @Valid AdminLoginInput adminLoginInput) throws Exception{
        AdminLoginOuput output = this.adminInteractor.logIn(adminLoginInput);
        return AppResponse.initResponse(
            HttpStatus.CREATED, 
            true, 
            "Login success", 
            output);
    }
}
