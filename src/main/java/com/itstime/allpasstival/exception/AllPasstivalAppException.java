package com.itstime.allpasstival.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AllPasstivalAppException extends RuntimeException{
    private ErrorCode errorCode;
    private String message;

    @Override
    public String toString() {
        if(message == null) return errorCode.getMessage();
        return String.format("%s %s", errorCode.getMessage(),message);
    }
}
