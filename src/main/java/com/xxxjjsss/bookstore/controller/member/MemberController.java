package com.xxxjjsss.bookstore.controller.member;

import com.xxxjjsss.bookstore.domain.member.Member;
import com.xxxjjsss.bookstore.dto.book.BookResponseDto;
import com.xxxjjsss.bookstore.dto.login.LoginRequestDto;
import com.xxxjjsss.bookstore.dto.login.LoginResponseDto;
import com.xxxjjsss.bookstore.dto.member.MemberDto;
import com.xxxjjsss.bookstore.dto.member.MemberRequestDto;
import com.xxxjjsss.bookstore.dto.member.MemberResponseDto;
import com.xxxjjsss.bookstore.global.RsData.ApiResponse;
import com.xxxjjsss.bookstore.global.rq.Rq;
import com.xxxjjsss.bookstore.global.security.SecurityUser;
import com.xxxjjsss.bookstore.service.member.MemberService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;
    private final Rq rq;


    @Getter
    @AllArgsConstructor
    public static class MembersResponse {
        private final List<MemberDto> members;
    }

    /**
     * 회원 목록 조회
     */
    @GetMapping
    public ApiResponse<MembersResponse> getAllMembers() {
        List<Member> members = memberService.getAllMembers();

        List<MemberDto> collect = members.stream()
                .map(MemberDto::new)
                .collect(Collectors.toList());

        return ApiResponse.success(new MembersResponse(collect));
    }

    @Getter
    @AllArgsConstructor
    public static class RegisterResponse {
        private final MemberResponseDto member;
    }

    /**
     * 회원가입
     */
    @PostMapping("/register")
    public ApiResponse<RegisterResponse> join(@Valid @RequestBody MemberRequestDto memberRequestDto) {
        Member member = memberService.join(memberRequestDto);
        return ApiResponse.success(new RegisterResponse(new MemberResponseDto(member)));
    }


    @Getter
    @AllArgsConstructor
    public static class LoginResponseBody {
        private final MemberResponseDto member;
    }

    /**
     * 로그인
     */
    @PostMapping("/login")
    public ApiResponse<LoginResponseBody> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        LoginResponseDto dto = memberService.login(loginRequestDto.getUsername(), loginRequestDto.getPassword());

        // 토큰 쿠키에 등록
        rq.setCrossDomainCookie("accessToken", dto.getAccessToken());
        rq.setCrossDomainCookie("refreshToken", dto.getRefreshToken());

        return ApiResponse.success(new LoginResponseBody(new MemberResponseDto(dto.getMember())));

    }


    /**
     * 로그아웃
     */
    @PostMapping("/logout")
    public ApiResponse<Void> logout() {
        rq.removeCrossDomainCookie("accessToken");
        rq.removeCrossDomainCookie("refreshToken");

        return ApiResponse.success(null);
    }

    @Getter
    @AllArgsConstructor
    public static class MeResponseBody {
        private final MemberDto member;
    }


    /**
     * 내 정보 조회
     * 로그인 확인
     */
    @GetMapping("/me")
    public ApiResponse<MeResponseBody> me() {
        Member member = rq.getMember();
        return ApiResponse.success(new MeResponseBody(new MemberDto(member)));
    }


/*
    @Getter
    @AllArgsConstructor
    public static class BooksResponse {
        private final List<BookResponseDto> books;
    }

    @GetMapping("/book")
    public ApiResponse<BooksResponse> getAllBooks(@AuthenticationPrincipal SecurityUser user) {
        List<BookResponseDto> result = memberService.getAllBooks(user);
        return ApiResponse.success(new BooksResponse(result));
    }
*/
}
