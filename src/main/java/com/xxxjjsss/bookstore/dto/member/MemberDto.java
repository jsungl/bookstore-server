package com.xxxjjsss.bookstore.dto.member;

import com.xxxjjsss.bookstore.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberDto {

    private Long id;
    private String username;
    private String email;
    private String nickname;
    private String role;

    @Builder
    public MemberDto(Member member) {
        this.id = member.getId();
        this.username = member.getMemberId();
        this.email = member.getEmail();
        this.nickname = member.getNickname();
        this.role = member.getRole().name();
    }
}
