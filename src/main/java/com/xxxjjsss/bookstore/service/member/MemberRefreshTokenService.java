package com.xxxjjsss.bookstore.service.member;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.xxxjjsss.bookstore.domain.member.Member;
import com.xxxjjsss.bookstore.global.exception.ApiException;
import com.xxxjjsss.bookstore.global.exception.ErrorCode;
import com.xxxjjsss.bookstore.global.jwt.JwtProvider;
import com.xxxjjsss.bookstore.repository.member.MemberRefreshTokenRepository;
import com.xxxjjsss.bookstore.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberRefreshTokenService {

    private final MemberRepository memberRepository;
    private final MemberRefreshTokenRepository memberRefreshTokenRepository;
    private final JwtProvider jwtProvider;

    /**
     * 리프레시 토큰 유효성 검증
     * 만료된 액세스 토큰 복호화
     * DB에 저장된 리프레시 토큰과 일치하는지 검사
     */
    @Transactional(readOnly = true)
    public void validateRefreshToken(String oldAccessToken, String refreshToken) throws JsonProcessingException {
        
        log.info("refresh token 유효성 검증");

        jwtProvider.validateRefreshToken(refreshToken);

        Long id = jwtProvider.decodeOldAccessToken(oldAccessToken);

        memberRefreshTokenRepository.findByMemberId(id)
                .filter(memberRefreshToken -> memberRefreshToken.validateRefreshToken(refreshToken))
                .orElseThrow(() -> new ApiException(ErrorCode.EXPIRED_REFRESH_TOKEN));
    }

    /**
     * 만료된 액세스 토큰 복호화
     * 액세스 토큰 재발급
     */
    @Transactional
    public String recreateAccessToken(String oldAccessToken) throws JsonProcessingException {

        log.info("access token 재발급");

        Long id = jwtProvider.decodeOldAccessToken(oldAccessToken);

        Member member = memberRepository.findById(id).orElseThrow(() -> new ApiException(ErrorCode.USER_NOT_FOUND));

        return jwtProvider.createAccessToken(member, 60 * 30);
    }
}
