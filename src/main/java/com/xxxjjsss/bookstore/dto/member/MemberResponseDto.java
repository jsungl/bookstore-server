package com.xxxjjsss.bookstore.dto.member;

import com.xxxjjsss.bookstore.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberResponseDto {

    private Long id;
    private String memberId;
    private String email;
    private String nickname;
    private String roleValue;

    @Builder
    public MemberResponseDto(Member member) {
        this.id = member.getId();
        this.memberId = member.getMemberId();
        this.email = member.getEmail();
        this.nickname = member.getNickname();
        this.roleValue = member.getRole().getValue();
    }
}
