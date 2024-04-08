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
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "E4001", "잘못된 요청입니다."),
    VALIDATION_FAILED(HttpStatus.BAD_REQUEST, "E4002", "유효성 검증에 실패하였습니다."),

    //401
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "E4011", "인증이 필요합니다."),
    INVALID_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, "E4012", "잘못된 접근입니다. 유효한 토큰이 아닙니다."),
    EXPIRED_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, "E4013", "액세스 토큰이 만료되었습니다. 새로고침하거나 다시 로그인해주세요."),

    //403
    FORBIDDEN(HttpStatus.FORBIDDEN, "E4031", "권한이 없습니다."),
    //NOT_MATCHED_WRITER(HttpStatus.FORBIDDEN, ""),

    //404
    BOOK_NOT_FOUND(HttpStatus.NOT_FOUND, "E4041", "해당 id의 책을 찾을 수 없습니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "E4042", "해당 유저를 찾을 수 없습니다."),

    //409
    BOOK_NAME_ALREADY_EXIST(HttpStatus.CONFLICT, "E4091", "이미 존재하는 제목입니다."),
    USER_ID_ALREADY_EXIST(HttpStatus.CONFLICT, "E4092", "이미 존재하는 아이디입니다."),
    USER_EMAIL_ALREADY_EXIST(HttpStatus.CONFLICT, "E4093", "이미 존재하는 이메일입니다."),
    USER_NAME_ALREADY_EXIST(HttpStatus.CONFLICT, "E4094", "이미 존재하는 닉네임입니다."),

    //500
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "E5001", "서버에 오류가 발생하였습니다.");

    private HttpStatus status;
    private String code;
    private String msg;
}
