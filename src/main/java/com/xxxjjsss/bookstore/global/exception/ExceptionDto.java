package com.xxxjjsss.bookstore.global.exception;

import lombok.Getter;

@Getter
public class ExceptionDto {

    private String errorCode;
    private String errorMessage;

    public ExceptionDto(ErrorCode errorCode) {
        this.errorCode = errorCode.getCode();
        this.errorMessage = errorCode.getMsg();
    }

    public ExceptionDto(ErrorCode errorCode, String message) {
        this.errorCode = errorCode.getCode();
        this.errorMessage = message;
    }

}
