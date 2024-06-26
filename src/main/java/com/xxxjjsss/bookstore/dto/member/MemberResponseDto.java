package com.xxxjjsss.bookstore.dto.member;

import com.xxxjjsss.bookstore.domain.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberResponseDto {

    private Long id;
    private String nickname;
    private String role;


    public MemberResponseDto(Member member) {
        this.id = member.getId();
        this.nickname = member.getNickname();
        this.role = member.getRole().name();
    }
}
