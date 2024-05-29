package com.xxxjjsss.bookstore.global.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.xxxjjsss.bookstore.global.exception.ApiException;
import com.xxxjjsss.bookstore.global.exception.ErrorCode;
import com.xxxjjsss.bookstore.global.rq.Rq;
import com.xxxjjsss.bookstore.global.security.SecurityUser;
import com.xxxjjsss.bookstore.service.member.MemberRefreshTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * 요청에 담긴 JWT를 검증하기 위한 커스텀 필터
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final MemberRefreshTokenService memberRefreshTokenService;
    private final Rq rq;
    private final JwtProvider jwtProvider;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

//        log.info("request={}", request.getRequestURI());

        // 로그인, 회원가입 제외
        if (request.getRequestURI().equals("/api/members/login") || request.getRequestURI().equals("/api/members/register")
            || request.getRequestURI().startsWith("/api/hc")) {
            filterChain.doFilter(request, response);
            return;
        }

//        log.info("==========인가/권한 검증 : JwtAuthorizationFilter 실행==========");

        String accessToken = rq.getCookieValue("accessToken");

        try {
            if (!accessToken.isBlank()) {

                if(!jwtProvider.validateAccessToken(accessToken)) {
                    // log.info("access token 만료");
                    String refreshToken = rq.getCookieValue("refreshToken");
                    memberRefreshTokenService.validateRefreshToken(accessToken, refreshToken);
                    accessToken = memberRefreshTokenService.recreateAccessToken(accessToken);
                    rq.setCrossDomainCookie("accessToken", accessToken);
                }

                // securityUser 생성
                SecurityUser securityUser = jwtProvider.getUserFromAccessToken(accessToken);

                //세션에 사용자 등록(로그인 처리)
                rq.setLogin(securityUser);
            }

        } catch (ApiException e) {
            rq.removeCrossDomainCookie("accessToken");
            rq.removeCrossDomainCookie("refreshToken");
            request.setAttribute("JwtException", e.getErrorCode());
        } catch (JsonProcessingException e) {
            request.setAttribute("JwtException", ErrorCode.WRONG_TYPE_TOKEN);
        }

        filterChain.doFilter(request, response);

    }
}
