package com.universityapp.common.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AppException extends RuntimeException {
    private HttpStatus status;
    private String message;

    public AppException(ErrorCode code) {
        super();
        this.status = code.getHttpStatus();
        this.message = code.getMessage();
    }

}
