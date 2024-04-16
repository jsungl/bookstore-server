package com.xxxjjsss.bookstore.dto.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Range;


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
//    @NotBlank(message = "Please enter a title.", groups = NotBlankGroup.class)
//    private String title;
//    @NotBlank(message = "Please enter a description.", groups = NotBlankGroup.class)
//    private String description;
//    @NotBlank(message = "Please enter a image URL.", groups = NotBlankGroup.class)
//    private String imageUrl;
//    @Range(min = 1, max = 999, message = "The price must be between ${min} and ${max}", groups = PriceRangeCheckGroup.class)
//    private Double price;

    @NotBlank(message = "Please enter a title.")
    private String title;
    @NotBlank(message = "Please enter a description.")
    private String description;
    @NotBlank(message = "Please enter a image URL.")
    private String imageUrl;
    @NotNull(message = "Please enter a price.")
    @Range(min = 1, max = 999, message = "The price must be between ${min} and ${max}")
    private Double price;

}
