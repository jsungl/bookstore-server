package com.xxxjjsss.bookstore.service.member;

import com.xxxjjsss.bookstore.domain.member.Member;
import com.xxxjjsss.bookstore.domain.member.Role;
import com.xxxjjsss.bookstore.dto.member.MemberRequestDto;
import com.xxxjjsss.bookstore.global.exception.DuplicatedException;
import com.xxxjjsss.bookstore.global.exception.ErrorCode;
import com.xxxjjsss.bookstore.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public Member join(MemberRequestDto memberRequestDto) {

        //중복검사
        Boolean isExistId = memberRepository.existsByMemberId(memberRequestDto.getMemberId());
        if(isExistId) {
            throw new DuplicatedException(ErrorCode.USER_ID_ALREADY_EXIST, "memberId");
        }

        Boolean isExistEmail = memberRepository.existsByEmail(memberRequestDto.getEmail());
        if(isExistEmail) {
            throw new DuplicatedException(ErrorCode.USER_EMAIL_ALREADY_EXIST, "email");
        }

        Boolean isExistName = memberRepository.existsByNickname(memberRequestDto.getNickname());
        if(isExistName) {
            throw new DuplicatedException(ErrorCode.USER_NAME_ALREADY_EXIST, "nickname");
        }

        Member member = Member.builder()
                .memberId(memberRequestDto.getMemberId())
                .password(passwordEncoder.encode(memberRequestDto.getPassword()))
                .email(memberRequestDto.getEmail())
                .nickname(memberRequestDto.getNickname())
                .role(Role.USER)
                .build();

        return memberRepository.save(member);

    }

    @Transactional(readOnly = true)
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }
}
