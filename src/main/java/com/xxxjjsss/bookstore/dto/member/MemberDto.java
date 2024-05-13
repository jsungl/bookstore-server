package com.xxxjjsss.bookstore.dto.member;

import com.xxxjjsss.bookstore.domain.member.Member;
import com.xxxjjsss.bookstore.dto.book.BookResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class MemberDto {

    private Long id;
    private String username;
    private String email;
    private String nickname;
    private String role;
    private List<BookResponseDto> books;
    private String createdDate;

    @Builder
    public MemberDto(Member member) {
        this.id = member.getId();
        this.username = member.getMemberId();
        this.email = member.getEmail();
        this.nickname = member.getNickname();
        this.role = member.getRole().name();
        this.books = member.getBooks().stream()
                .map(book -> BookResponseDto.builder()
                        .book(book)
                        .build())
                .collect(Collectors.toList());
        this.createdDate = member.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
