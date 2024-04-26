package com.xxxjjsss.bookstore.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 예외처리 응답에 대한 상세 설명을 위해 Enum 클래스 정의
 * 응답 오류 코드 정의
 * 프로그램 전체에서 일관된 방식으로 예외를 처리할 수 있으며, 특정 예외가 발생했을 때 필요한 모든 정보를 한 곳에서 관리할 수 있다
 */
@Getter
@AllArgsConstructor
public enum ErrorCode {

    //400
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "E4001", "Invalid request. The command could not be processed."),
    VALIDATION_FAILED(HttpStatus.BAD_REQUEST, "E4002", "Validation failed. Please check your input and try again."),


    //401
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "E4011", "Authentication Required. Please log in or sign up to continue."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "E4012", "Invalid Token."),
    EXPIRED_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, "E4013", "Your access token has expired. Please refresh the page or log in again."),
    WRONG_TYPE_TOKEN(HttpStatus.UNAUTHORIZED, "E4014", "Wrong Type Token."),
    PASSWORD_NOT_MATCHED(HttpStatus.UNAUTHORIZED, "E4015", "Incorrect Password. The password you entered is incorrect."),
    EXPIRED_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "E4016", "Your refresh token has expired. Please log in again."),


    //403
    FORBIDDEN(HttpStatus.FORBIDDEN, "E4031", "Access Denied. You do not have the necessary permissions to access this resource."),
    //NOT_MATCHED_WRITER(HttpStatus.FORBIDDEN, ""),

    //404
    BOOK_NOT_FOUND(HttpStatus.NOT_FOUND, "E4041", "Book was not found."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "E4042", "User Not Found. The user with the provided ID could not be found."),

    //409
    BOOK_NAME_ALREADY_EXIST(HttpStatus.CONFLICT, "E4091", "The Title you entered already exists."),
    USER_ID_ALREADY_EXIST(HttpStatus.CONFLICT, "E4092", "The ID you entered already exists."),
    USER_EMAIL_ALREADY_EXIST(HttpStatus.CONFLICT, "E4093", "The Email you entered already exists."),
    USER_NAME_ALREADY_EXIST(HttpStatus.CONFLICT, "E4094", "The Nickname you entered already exists."),

    //500
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "E5001", "Internal Server Error. The server encountered an internal error or misconfiguration and was unable to complete your request.");

    private HttpStatus status;
    private String code;
    private String msg;
}
