package com.xxxjjsss.bookstore.dto.member;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberRequestDto {

    @NotBlank(message = "Please enter a ID.")
    @Pattern(regexp = "^[a-z0-9]{4,12}$", message = "ID must be 4 to 12 characters using only English lowercase letters and numbers.")
    private String memberId;

    @NotBlank(message = "Please enter a password.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,12}$", message = "Password must be 8 to 12 characters, including at least one English case, number, and special character.")
    private String password;

    @NotBlank(message = "Please enter a email.")
    private String email;

    @NotBlank(message = "Please enter a nickname.")
    @Pattern(regexp = "^[가-힣a-zA-Z0-9]{2,8}$", message = "Nicknames must be 2 to 8 characters without special characters.")
    private String nickname;
}
