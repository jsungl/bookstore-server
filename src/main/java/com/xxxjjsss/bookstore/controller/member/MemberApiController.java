package com.xxxjjsss.bookstore.controller.member;

import com.xxxjjsss.bookstore.domain.member.Member;
import com.xxxjjsss.bookstore.dto.member.MemberRequestDto;
import com.xxxjjsss.bookstore.dto.member.MemberResponseDto;
import com.xxxjjsss.bookstore.global.RsData.ApiResponse;
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


    @Getter
    @AllArgsConstructor
    class Result<T> {
        private T members;
    }

    /**
     * 목록 조회
     */
    @GetMapping
    public ApiResponse<Result> getAllMembers() {
        List<Member> members = memberService.getAllMembers();
        List<MemberResponseDto> collect = members.stream()
                .map(m -> MemberResponseDto.builder().member(m).build())
                .collect(Collectors.toList());

        return ApiResponse.success(new Result(collect));
    }

    @Getter
    @AllArgsConstructor
    public static class MemberResponse {
        private final MemberResponseDto member;
    }

    /**
     * 회원가입
     */
    @PostMapping
    public ApiResponse<MemberResponse> addMember(@Valid @RequestBody MemberRequestDto memberRequestDto) {
        Member member = memberService.join(memberRequestDto);
        MemberResponseDto dto = MemberResponseDto.builder().member(member).build();
        return ApiResponse.success(new MemberResponse(dto));

    }


}
