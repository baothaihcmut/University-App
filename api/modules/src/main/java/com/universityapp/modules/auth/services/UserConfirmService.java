package com.universityapp.modules.auth.services;

import com.universityapp.modules.users.entities.User;

public interface UserConfirmService {
    String storeUser(User user) throws Exception;

    User getUser(String code) throws Exception;

    boolean isUserPendingVerification(String email) throws Exception;

    void sendEmail(String code, String email, String firstName, String lastName) throws Exception;
}
