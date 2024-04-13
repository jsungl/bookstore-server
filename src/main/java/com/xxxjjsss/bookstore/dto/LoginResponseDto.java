package com.xxxjjsss.bookstore.dto;

import com.xxxjjsss.bookstore.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponseDto {

    private Member member;
    private String accessToken;
    private String refreshToken;

}
