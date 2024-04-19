package com.xxxjjsss.bookstore.domain.member;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class MemberRefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String token;

    public MemberRefreshToken(Member member, String refreshToken) {
        this.member = member;
        this.token = refreshToken;
    }

    public void updateRefreshToken(String refreshToken) {
        this.token = refreshToken;
    }

    public boolean validateRefreshToken(String refreshToken) {
        return this.token.equals(refreshToken);
    }

}
