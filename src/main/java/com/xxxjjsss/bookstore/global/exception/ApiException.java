package com.xxxjjsss.bookstore.global.exception;

import lombok.Getter;

/**
 * 사용자 정의 예외 클래스
 * 생성자를 통해 ErrorCode 인스턴스를 받아 초기화하고, 이 값을 기반으로 HTTP 상태코드나 오류 메시지 등 필요한 정보를 제공한다
 * 개발자가 의도한 예외를 비즈니스 로직에서 적절한 오류코드를 담아 발생시키면 @RestControllerAdvice 가 적절한 오류 응답을 하게된다
 */

@Getter
public class ApiException extends RuntimeException {

    private ErrorCode errorCode;

    public ApiException(ErrorCode errorCode) {
        super(errorCode.getMsg());
        this.errorCode = errorCode;
    }

    public ApiException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

}
