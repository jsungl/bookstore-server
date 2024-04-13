package com.xxxjjsss.bookstore.global.jwt;

import com.xxxjjsss.bookstore.domain.member.Member;
import com.xxxjjsss.bookstore.global.exception.ApiException;
import com.xxxjjsss.bookstore.global.exception.ErrorCode;
import com.xxxjjsss.bookstore.global.security.SecurityUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class JwtProvider {

    private SecretKey secretKey;

    public JwtProvider(@Value("${spring.jwt.secret}") String secret) {
        secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    public Claims getClaims(String token) {
        return  Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
    }


    public Boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
        } catch (MalformedJwtException e) {
            throw new ApiException(ErrorCode.WRONG_TYPE_TOKEN);
        } catch (SignatureException e) {
            throw new ApiException(ErrorCode.INVALID_ACCESS_TOKEN);
        } catch (ExpiredJwtException e) {
            throw new ApiException(ErrorCode.EXPIRED_ACCESS_TOKEN);
        }

        return true;
    }

    public String createJwt(Member member, Long expiredMs) {

        return Jwts.builder()
                .claim("id", member.getId())
                .claim("username", member.getMemberId())
                .claim("role", member.getRole().name())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiredMs))
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

//        authorities.forEach(authority->log.info("authorities={}", authority));

        return new SecurityUser(id, username, "", authorities);
    }
}
