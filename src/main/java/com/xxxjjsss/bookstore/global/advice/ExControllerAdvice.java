package com.xxxjjsss.bookstore.global.advice;

import com.xxxjjsss.bookstore.global.RsData.ApiResponse;
import com.xxxjjsss.bookstore.global.exception.ApiException;
import com.xxxjjsss.bookstore.global.exception.ErrorCode;
import com.xxxjjsss.bookstore.global.exception.ExceptionDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 오류 응답
 */
@Slf4j
@RestControllerAdvice
public class ExControllerAdvice {


    @Getter
    @AllArgsConstructor
    public static class ErrorResponse {
        private final ExceptionDto error;
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse> ApiExHandle(ApiException e) {
        ExceptionDto exceptionDto = new ExceptionDto(e.getErrorCode());

        ApiResponse<ErrorResponse> result = ApiResponse.fail(new ErrorResponse(exceptionDto));

        return ResponseEntity
                .status(e.getErrorCode().getStatus())
                .body(result);
    }


    //유효성 검증 실패
    //@ExceptionHandler(MethodArgumentNotValidException.class)


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> ExHandle(Exception e) {

        log.error("error message {}", e.getMessage());

        ExceptionDto exceptionDto = new ExceptionDto(ErrorCode.INTERNAL_SERVER_ERROR);
        ApiResponse<ErrorResponse> result = ApiResponse.fail(new ErrorResponse(exceptionDto));

        return ResponseEntity.status(ErrorCode.INTERNAL_SERVER_ERROR.getStatus())
                .body(result);
    }

}
