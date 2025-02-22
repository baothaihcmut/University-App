
package com.universityapp.modules.users.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.universityapp.common.response.AppResponse;
import com.universityapp.modules.users.interactors.UserInteractor;
import com.universityapp.modules.users.presenters.input.DependentInput;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

     private final UserInteractor userInteractor;

     @PostMapping("/dependents")
     public ResponseEntity<AppResponse> getDependent(@RequestParam DependentInput input)
               throws Exception {
          return AppResponse.initResponse(HttpStatus.CREATED, true, "Login success",
                    this.userInteractor.createDependent(input));
     }

}
