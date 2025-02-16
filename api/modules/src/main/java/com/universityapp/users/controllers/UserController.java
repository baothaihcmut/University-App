package com.universityapp.users.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.universityapp.auth.dtos.request.LoginRequestDTO;
import com.universityapp.common.response.AppResponse;
import com.universityapp.users.services.impl.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
     private final UserService userService;

     @PostMapping("/log-in")
     public ResponseEntity<AppResponse> logIn(@RequestBody @Valid LoginRequestDTO dto) throws Exception {
          return AppResponse.initResponse(HttpStatus.CREATED, true, "Login success",
                    this.userService.findUserByPhoneNumber("sss"));
     }

}
