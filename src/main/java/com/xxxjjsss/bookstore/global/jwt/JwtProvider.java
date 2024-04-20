package com.xxxjjsss.bookstore.global.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xxxjjsss.bookstore.domain.member.Member;
import com.xxxjjsss.bookstore.global.exception.ApiException;
import com.xxxjjsss.bookstore.global.exception.ErrorCode;
import com.xxxjjsss.bookstore.global.security.SecurityUser;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Component
@Slf4j
public class JwtProvider {

    private SecretKey secretKey;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public JwtProvider(@Value("${spring.jwt.secret}") String secret) {
        secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    public Claims getClaims(String token) {
        return  Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
    }


    public Boolean validateAccessToken(String token) {
        try {
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
        } catch (MalformedJwtException e) {
            throw new ApiException(ErrorCode.WRONG_TYPE_TOKEN);
        } catch (SignatureException e) {
            throw new ApiException(ErrorCode.INVALID_TOKEN);
        } catch (ExpiredJwtException e) {
            return false;
        }

        return true;
    }

    public void validateRefreshToken(String refreshToken) {

        try {
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(refreshToken);
        } catch (MalformedJwtException e) {
            throw new ApiException(ErrorCode.WRONG_TYPE_TOKEN);
        } catch (SignatureException e) {
            throw new ApiException(ErrorCode.INVALID_TOKEN);
        } catch (ExpiredJwtException e) {
            throw new ApiException(ErrorCode.EXPIRED_REFRESH_TOKEN);
        }
    }


    public String createAccessToken(Member member, int expiredMs) {
        //JWT에는 변하지 않는 내용만 넣어준다.
        //id, username, role 등
        return Jwts.builder()
                .claim("id", member.getId())
                .claim("username", member.getMemberId())
                .claim("role", member.getRole().getValue())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000L * expiredMs))
                .signWith(secretKey)
                .compact();
    }

    public String createRefreshToken(int expiredMs) {
        //리프레시 토큰은 사용자와 관련된 정보를 담지 않고, 만료시간만 설정한다
        return Jwts.builder()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000L * expiredMs))
                .signWith(secretKey)
                .compact();
    }

    public SecurityUser getUserFromAccessToken(String accessToken) {

        Claims claims = getClaims(accessToken);
        String username = claims.get("username", String.class);
        String role = claims.get("role", String.class);
        Long id = claims.get("id", Long.class);

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));

        return new SecurityUser(id, username, "", authorities);
    }


    public Long decodeOldAccessToken(String oldAccessToken) throws JsonProcessingException {

        String decodeString = objectMapper.readValue(
                new String(Base64.getDecoder().decode(oldAccessToken.split("\\.")[1]), StandardCharsets.UTF_8),
                Map.class
        ).get("id").toString();

        return Long.parseLong(decodeString);
    }

}
