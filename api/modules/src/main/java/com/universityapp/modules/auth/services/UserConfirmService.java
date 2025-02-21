package com.universityapp.modules.auth.services;

import com.universityapp.modules.users.entities.User;

public interface UserConfirmService {
    String storeUser(User user) throws Exception;

    void sendEmail(String code, String email, String firstName, String lastName) throws Exception;
}
