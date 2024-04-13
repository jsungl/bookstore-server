package com.xxxjjsss.bookstore.controller.member;

import com.xxxjjsss.bookstore.domain.member.Member;
import com.xxxjjsss.bookstore.dto.LoginRequestDto;
import com.xxxjjsss.bookstore.dto.LoginResponseDto;
import com.xxxjjsss.bookstore.dto.member.MemberRequestDto;
import com.xxxjjsss.bookstore.dto.member.MemberResponseDto;
import com.xxxjjsss.bookstore.global.RsData.ApiResponse;
import com.xxxjjsss.bookstore.global.rq.Rq;
import com.xxxjjsss.bookstore.service.member.MemberService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/members")
public class MemberApiController {

    private final MemberService memberService;
    private final Rq rq;


    @Getter
    @AllArgsConstructor
    public static class MembersResponse {
        private final List<MemberResponseDto> members;
    }

    /**
     * 목록 조회
     */
    @GetMapping
    public ApiResponse<MembersResponse> getAllMembers() {
        List<Member> members = memberService.getAllMembers();
/*
        List<MemberResponseDto> collect = members.stream()
                .map(m -> MemberResponseDto.builder().member(m).build())
                .collect(Collectors.toList());
*/

        List<MemberResponseDto> collect = members.stream()
                .map(MemberResponseDto::new)
                .collect(Collectors.toList());

        return ApiResponse.success(new MembersResponse(collect));
    }

    @Getter
    @AllArgsConstructor
    public static class MemberResponse {
        private final MemberResponseDto member;
    }

    /**
     * 회원가입
     */
    @PostMapping("/register")
    public ApiResponse<MemberResponse> addMember(@Valid @RequestBody MemberRequestDto memberRequestDto) {
        Member member = memberService.join(memberRequestDto);
//        MemberResponseDto dto = MemberResponseDto.builder().member(member).build();
        return ApiResponse.success(new MemberResponse(new MemberResponseDto(member)));
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

        return ApiResponse.success(new LoginResponseBody(new MemberResponseDto(dto.getMember())));

    }

    @Getter
    @AllArgsConstructor
    public static class MeResponseBody {
        private final MemberResponseDto member;
    }

    @GetMapping("/me")
    public ApiResponse<MeResponseBody> me() {
        Member member = rq.getMember();
        log.info("member={}", member);

        return ApiResponse.success(new MeResponseBody(new MemberResponseDto(member)));
    }
}
