package com.xxxjjsss.bookstore.repository.member;

import com.xxxjjsss.bookstore.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    //아이디 중복검사
    Boolean existsByMemberId(String memberId);
    //이메일 중복검사
    Boolean existsByEmail(String email);
    //닉네임 중복검사
    Boolean existsByNickname(String nickname);

    //memberId를 받아 DB 테이블에서 회원을 조회하는 메소드 작성
    Optional<Member> findByMemberId(String memberId);
}
