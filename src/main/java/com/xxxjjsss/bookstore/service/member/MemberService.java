package com.xxxjjsss.bookstore.service.member;

import com.xxxjjsss.bookstore.domain.member.Member;
import com.xxxjjsss.bookstore.domain.member.Role;
import com.xxxjjsss.bookstore.dto.LoginResponseDto;
import com.xxxjjsss.bookstore.dto.member.MemberRequestDto;
import com.xxxjjsss.bookstore.global.exception.ApiException;
import com.xxxjjsss.bookstore.global.exception.DuplicatedException;
import com.xxxjjsss.bookstore.global.exception.ErrorCode;
import com.xxxjjsss.bookstore.global.jwt.JwtProvider;
import com.xxxjjsss.bookstore.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

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
    public LoginResponseDto login(String username, String password) {
//        Member member = memberRepository.findByMemberId(username).orElseThrow(() -> new UsernameNotFoundException("member is not exist"));
        Member member = memberRepository.findByMemberId(username).orElseThrow(() -> new ApiException(ErrorCode.USER_NOT_FOUND));

        if (!passwordEncoder.matches(password, member.getPassword())) {
//            throw new BadCredentialsException("password not match");
            throw new ApiException(ErrorCode.PASSWORD_NOT_MATCHED);
        }

        //String accessToken = jwtUtil.createJwt(member.getMemberId(), member.getRole().name(), 60 * 60 * 1000L);
        String accessToken = jwtProvider.createJwt(member, 60 * 60 * 1000L);

        return new LoginResponseDto(member, accessToken, "");
    }


    @Transactional(readOnly = true)
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Member> getMember(String username) {
        return memberRepository.findByMemberId(username);
    }
}
