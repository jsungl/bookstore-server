package com.xxxjjsss.bookstore.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginRequestDto {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
