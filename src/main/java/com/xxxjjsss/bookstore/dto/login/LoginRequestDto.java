package com.xxxjjsss.bookstore.dto.login;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginRequestDto {

    private String username;
    private String password;
}
