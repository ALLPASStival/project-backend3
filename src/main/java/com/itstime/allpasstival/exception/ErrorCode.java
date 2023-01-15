package com.itstime.allpasstival.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    DUPLICATED_USER_EMAIL(HttpStatus.CONFLICT,"User email duplicated"),
    USERNAME_NOT_FOUND(HttpStatus.NOT_FOUND,"user doesn't exist"),
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND,"category doesn't exist"),
    STATE_NOT_FOUND(HttpStatus.NOT_FOUND,"state doesn't exist"),
    FESTIVAL_NOT_FOUND(HttpStatus.NOT_FOUND,"festival doesn't exist"),
    DUPLICATED_USER_NICKNAME(HttpStatus.CONFLICT,"User nickname duplicated"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "user doesn't exist"),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "password is not matched"),
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "post doesn't exist"),
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "comment doesn't exist"),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "Token is not valid."),
    INVALID_PERMISSION(HttpStatus.UNAUTHORIZED, "User authorization failed"),
    DATABASE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "database error")
    ;
    private HttpStatus status;
    private String message;
}