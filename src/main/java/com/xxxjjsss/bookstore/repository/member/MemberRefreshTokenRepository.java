package com.xxxjjsss.bookstore.repository.member;

import com.xxxjjsss.bookstore.domain.member.MemberRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRefreshTokenRepository extends JpaRepository<MemberRefreshToken, Long> {

    Optional<MemberRefreshToken> findByMemberId(Long id);


    void deleteByMemberId(Long id);
}
