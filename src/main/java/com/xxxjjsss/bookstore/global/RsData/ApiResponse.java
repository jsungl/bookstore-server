package com.xxxjjsss.bookstore.global.RsData;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * 공통 응답 객체(API 응답 클래스)
 * Generic Interface에 <T>를 지정하여(Generic 타입으로 설정하여) 어떤 형태의 값도 넣을 수 있도록 구현한다.
 *
 */

@Getter
@AllArgsConstructor
public class ApiResponse<T> {

    //응답 성공 여부(성공:true/실패:false)
    private Boolean success;
    //응답 코드 번호(성공: >=0/실패: <0)
    private int code;
    //응답 결과 데이터
    private T data;


    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, 0, data);
    }

    public static <T> ApiResponse<T> fail(T data) {
        return new ApiResponse<>(false, -1, data);
    }


}
