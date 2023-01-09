package com.itstime.allpasstival.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    DUPLICATED_USER_EMAIL(HttpStatus.CONFLICT,"User email duplicated"),
    DUPLICATED_USER_NICKNAME(HttpStatus.CONFLICT,"User nickname duplicated"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "user doesn't exist"),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "password is not matched"),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "Token is not valid."),
    INVALID_PERMISSION(HttpStatus.UNAUTHORIZED, "User authorization failed"),
    DATABASE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "database error")
    ;
    private HttpStatus status;
    private String message;
}