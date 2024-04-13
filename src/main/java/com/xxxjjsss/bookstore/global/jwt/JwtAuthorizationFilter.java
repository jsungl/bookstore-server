package com.xxxjjsss.bookstore.global.jwt;

import com.xxxjjsss.bookstore.domain.member.Member;
import com.xxxjjsss.bookstore.domain.member.Role;
import com.xxxjjsss.bookstore.global.exception.ApiException;
import com.xxxjjsss.bookstore.global.rq.Rq;
import com.xxxjjsss.bookstore.global.security.CustomUserDetails;
import com.xxxjjsss.bookstore.global.security.SecurityUser;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final Rq rq;
    //private final JWTUtil jwtUtil;
    private final JwtProvider jwtProvider;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 로그인, 로그아웃 제외
        if (request.getRequestURI().equals("/api/members/login") || request.getRequestURI().equals("/api/members/logout")) {
            filterChain.doFilter(request, response);
            return;
        }

        log.info("==========인가/권한 검증 : JwtAuthorizationFilter 실행==========");

        String accessToken = rq.getCookieValue("accessToken", "");

        try {
            if (!accessToken.isBlank()) {

                if(jwtProvider.validateToken(accessToken)) {

/*
                    String username = jwtUtil.getUsername(accessToken);
                    String role = jwtUtil.getRole(accessToken);

                    Member member = new Member(username, "", Role.valueOf(role));
                    //UserDetails에 회원 정보 객체 담기
                    CustomUserDetails userDetails = new CustomUserDetails(member);
                    //스프링 시큐리티 인증 토큰 생성
                    Authentication authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    rq.setLogin(authToken);
*/

                    // securityUser 가져오기
                    SecurityUser securityUser = jwtProvider.getUserFromAccessToken(accessToken);

                    //세션에 사용자 등록(로그인 처리)
                    rq.setLogin(securityUser);
                }
            }

        } catch (ApiException e) {
            request.setAttribute("JwtException", e.getErrorCode());
        }

        filterChain.doFilter(request, response);

    }
}
