package com.xxxjjsss.bookstore.global.advice;

import com.xxxjjsss.bookstore.global.RsData.ApiResponse;
import com.xxxjjsss.bookstore.global.exception.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

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

    @Getter
    @AllArgsConstructor
    class Result<T> {
        private T error;
    }


    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse> ApiExHandle(ApiException e) {
        ExceptionDto exceptionDto = new ExceptionDto(e.getErrorCode());

        ApiResponse<ErrorResponse> result = ApiResponse.fail(new ErrorResponse(exceptionDto));

        return ResponseEntity
                .status(e.getErrorCode().getStatus())
                .body(result);
    }

    //중복 검사
    @ExceptionHandler(DuplicatedException.class)
    public ResponseEntity<ApiResponse> DuplicatedExHandle(DuplicatedException e) {

        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("errorCode", e.getErrorCode().getCode());
        errorMap.put("errorMessage", e.getErrorCode().getMsg());
        errorMap.put("errorField", e.getErrorField());

        ApiResponse<Result> result = ApiResponse.fail(new Result(errorMap));

        return ResponseEntity
                .status(e.getErrorCode().getStatus())
                .body(result);
    }


    //유효성 검증 실패
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> MethodArgumentNotValidExHandle(MethodArgumentNotValidException e) {

        Map<String, Object> errorMap = new HashMap<>();
        Map<String, Object> errorFieldMap = new HashMap<>();
        for(FieldError error : e.getBindingResult().getFieldErrors()) {
            errorFieldMap.put(error.getField(), error.getDefaultMessage());
        }

        errorMap.put("errorCode", ErrorCode.VALIDATION_FAILED.getCode());
        errorMap.put("errorMessage", ErrorCode.VALIDATION_FAILED.getMsg());
        errorMap.put("errorField", errorFieldMap);

        ApiResponse<Result> result = ApiResponse.fail(new Result(errorMap));

        return ResponseEntity
                .status(ErrorCode.VALIDATION_FAILED.getStatus())
                .body(result);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> ExHandle(Exception e) {

        log.error("error", e);

        ExceptionDto exceptionDto = new ExceptionDto(ErrorCode.INTERNAL_SERVER_ERROR);
        ApiResponse<ErrorResponse> result = ApiResponse.fail(new ErrorResponse(exceptionDto));

        return ResponseEntity.status(ErrorCode.INTERNAL_SERVER_ERROR.getStatus())
                .body(result);
    }

}
