
package com.universityapp.modules.users.controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.universityapp.common.response.AppResponse;
import com.universityapp.modules.users.interactors.UserInteractor;
import com.universityapp.modules.users.presenters.input.DependentInput;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

     private final UserInteractor userInteractor;

     @PostMapping("/dependents")
     public ResponseEntity<AppResponse> getDependent(@RequestBody @Valid DependentInput input) throws Exception {
          return AppResponse.initResponse(HttpStatus.CREATED, true, "Dependent created",
                    this.userInteractor.createDependent(input));
     }

     @GetMapping("dependents")
     public ResponseEntity<AppResponse> getDependent() throws Exception {
          return AppResponse.initResponse(HttpStatus.OK, true, "get dependents by userId",
                    this.userInteractor.getDependentsByUserId());
     }

     @DeleteMapping("/dependents/{id}")
     public ResponseEntity<AppResponse> deleteDependent(@PathVariable("id") UUID dependent_id) throws Exception {
          return AppResponse.initResponse(HttpStatus.NO_CONTENT);
     }

}
