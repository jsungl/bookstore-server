package com.xxxjjsss.bookstore.dto.login;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginRequestDto {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
