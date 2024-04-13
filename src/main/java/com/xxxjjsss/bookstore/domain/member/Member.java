package com.xxxjjsss.bookstore.domain.member;

import com.xxxjjsss.bookstore.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@ToString
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String memberId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public Member(String memberId, String password, Role role) {
        this.memberId = memberId;
        this.password = password;
        this.role = role;
    }

}
