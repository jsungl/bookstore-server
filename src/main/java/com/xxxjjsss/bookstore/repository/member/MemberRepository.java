package com.xxxjjsss.bookstore.repository.member;

import com.xxxjjsss.bookstore.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    //아이디 중복검사
    Boolean existsByMemberId(String memberId);
    //이메일 중복검사
    Boolean existsByEmail(String email);
    //닉네임 중복검사
    Boolean existsByNickname(String nickname);
}
