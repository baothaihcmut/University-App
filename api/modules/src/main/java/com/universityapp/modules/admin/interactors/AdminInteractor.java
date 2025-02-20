package com.universityapp.modules.admin.interactors;

import org.springframework.stereotype.Service;

import com.universityapp.modules.admin.presenters.input.AdminLoginInput;
import com.universityapp.modules.admin.presenters.output.AdminLoginOuput;

@Service
public interface AdminInteractor {
    AdminLoginOuput logIn(AdminLoginInput input) throws Exception;
}
