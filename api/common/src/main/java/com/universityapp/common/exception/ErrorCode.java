package com.universityapp.common.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ErrorCode {
    BAD_CREDENTIALS_EXCEPTION(HttpStatus.UNAUTHORIZED, "Email or password is incorrect"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not found"),
    EMAIL_EXIST(HttpStatus.CONFLICT, "email exist"),
    PHONE_NUMBER_EXIST(HttpStatus.CONFLICT, "phone number exist"),
    USER_PENDING_VERFICATION_SIGN_UP(HttpStatus.CONFLICT,"user is pending for verification"),
    ACCOUNT_NOT_IN_SYTEM(HttpStatus.UNAUTHORIZED, "account not found in system or has been activated"),
    WRONG_VERIFICATION_CODE(HttpStatus.UNAUTHORIZED,"wrong verification code")
    ;

    private HttpStatus status;
    private String message;

    public HttpStatus getHttpStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
