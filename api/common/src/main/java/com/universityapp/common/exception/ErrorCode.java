package com.universityapp.common.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ErrorCode {
    BAD_CREDENTIALS_EXCEPTION(HttpStatus.UNAUTHORIZED, "Email or password is incorrect"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not found"),
    EMAIL_EXIST(HttpStatus.CONFLICT, "email exist"),
    PHONE_NUMBER_EXIST(HttpStatus.CONFLICT, "phone number exist");

    private HttpStatus status;
    private String message;

    public HttpStatus getHttpStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
