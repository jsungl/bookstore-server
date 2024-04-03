package com.xxxjjsss.bookstore.dto;

import lombok.*;

/**
 * "cannot deserialize from object value (no delegate- or property-based creator)" 와 같은 에러 발생한다면
 * API 요청 시 Request Body의 JSON 데이터를 DTO(Java Object)로 변환을 해주는 이때 DTO의 기본 생성자를 찾을 수 없어서 나는 에러이다.
 *
 * 즉, POST 요청 @RequestBody 로 넘어오는 객체에는 기본 생성자가 필요하다.
 * Protected 타입 기본 생성자를 생성해주거나, @NoArgsConstructor(access = AccessLevel.PROTECTED) 어노테이션 활용
 */

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookRequestDto {

    private String title;
    private String description;
    private String imageUrl;
    private Double price;

}
