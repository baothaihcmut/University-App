package com.universityapp.admin.interactors;

import org.springframework.stereotype.Service;

import com.universityapp.admin.presenters.input.AdminLoginInput;
import com.universityapp.admin.presenters.output.AdminLoginOuput;

@Service
public interface AdminInteractor {
    AdminLoginOuput logIn(AdminLoginInput input) throws Exception;
}
