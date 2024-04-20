package com.xxxjjsss.bookstore.dto.login;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginRequestDto {

    @NotBlank(message = "Please enter a ID.")
    private String username;
    @NotBlank(message = "Please enter a password.")
    private String password;
}
