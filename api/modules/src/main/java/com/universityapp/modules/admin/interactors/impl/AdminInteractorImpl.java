package com.universityapp.modules.admin.interactors.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.universityapp.common.enums.Role;
import com.universityapp.common.exception.AppException;
import com.universityapp.common.exception.ErrorCode;
import com.universityapp.common.logger.LoggerUtil;
import com.universityapp.modules.admin.entities.Admin;
import com.universityapp.modules.admin.interactors.AdminInteractor;
import com.universityapp.modules.admin.presenters.input.AdminLoginInput;
import com.universityapp.modules.admin.presenters.output.AdminLoginOuput;
import com.universityapp.modules.admin.repositories.AdminRepository;
import com.universityapp.modules.auth.services.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminInteractorImpl implements AdminInteractor{
    private final AdminRepository adminRepository;
    private final AuthService authService;
    private final Logger logger = LoggerFactory.getLogger(AdminInteractorImpl.class);

    @Override
    public AdminLoginOuput logIn(AdminLoginInput input) throws Exception {
        //get admin by email
        Admin admin = this.adminRepository
                        .findAdminByEmail(input.getEmail())
                        .orElseThrow(()->{
                            LoggerUtil.warn(
                                logger, 
                                "user login fail",
                                Map.of(
                                    "email", input.getEmail(),
                                    "detail","Wrong email"
                                ));
                            return new AppException(ErrorCode.BAD_CREDENTIALS_EXCEPTION);
                        });
        //check password
        if(!this.authService.checkPasswordMatch(input.getPassword(), admin.getPassword())) {
            LoggerUtil.warn(
                                logger, 
                                "user login fail",
                                Map.of(
                                    "email", input.getEmail(),
                                    "detail","Wrong password"
                                ));
            throw new AppException(ErrorCode.BAD_CREDENTIALS_EXCEPTION);
        }
        //gen token
        String accessToken = this.authService.genAccessToken(admin.getId(), Role.ADMIN);
        String refreshToken = this.authService.genRefreshToken(admin.getId());
        //set current refresh token for admin
        admin.setCurrentRefreshToken(refreshToken);
        //save to db
        this.adminRepository.save(admin);
        return AdminLoginOuput.builder()
                              .accessToken(accessToken)
                              .refreshToken(refreshToken)
                              .build();
    }



}
