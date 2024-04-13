package com.xxxjjsss.bookstore.global.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xxxjjsss.bookstore.global.exception.ErrorCode;
import com.xxxjjsss.bookstore.global.exception.ExceptionDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
@Slf4j
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        log.info("인증 실패!");
        log.info("AuthenticationEntryPoint() 실행");

        ErrorCode errorCode = (ErrorCode) request.getAttribute("JwtException");
        //log.info("errorCode={}", errorCode);
        //null or EXPIRED_ACCESS_TOKEN ..etc

        response.setStatus(errorCode == null ? ErrorCode.UNAUTHORIZED.getStatus().value() : errorCode.getStatus().value());
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        Map<String, ExceptionDto> error = Map.of("error", new ExceptionDto(errorCode == null ? ErrorCode.UNAUTHORIZED : errorCode));
        ObjectMapper mapper = new ObjectMapper();
/*
        OutputStream outputStream = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();

        mapper.writeValue(
                outputStream,
                Map.of(
                        "success", false,
                        "code", -1,
                        "data", error
                )
        );
        outputStream.flush();
*/

        response.getWriter().write(
                mapper.writeValueAsString(Map.of(
                        "success", false,
                        "code", -1,
                        "data", error
                ))
        );

        response.getWriter().flush();

    }
}
