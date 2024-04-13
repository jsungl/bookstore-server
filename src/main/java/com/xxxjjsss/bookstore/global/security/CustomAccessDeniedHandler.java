package com.xxxjjsss.bookstore.global.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xxxjjsss.bookstore.global.exception.ErrorCode;
import com.xxxjjsss.bookstore.global.exception.ExceptionDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
@Slf4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.info("인가 실패! 접근 권한이 없습니다.");
        log.info("AccessDeniedHandler() 실행");

        response.setStatus(ErrorCode.FORBIDDEN.getStatus().value());
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        Map<String, ExceptionDto> error = Map.of("error", new ExceptionDto(ErrorCode.FORBIDDEN));
        ObjectMapper mapper = new ObjectMapper();
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
