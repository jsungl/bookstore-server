package com.xxxjjsss.bookstore.global.exception;

import lombok.Getter;

@Getter
public class ExceptionDto {

    private String errorCode;
    private String errorMessage;
//    private String errorField;
    private Object errorField;


    public ExceptionDto(ErrorCode errorCode) {
        this.errorCode = errorCode.getCode();
        this.errorMessage = errorCode.getMsg();
        this.errorField = null;
    }

    public ExceptionDto(ErrorCode errorCode, String message) {
        this.errorCode = errorCode.getCode();
        this.errorMessage = message;
        this.errorField = null;
    }

    public ExceptionDto(ErrorCode errorCode, Object errorField) {
        this.errorCode = errorCode.getCode();
        this.errorMessage = errorCode.getMsg();
        this.errorField = errorField;
    }

}
