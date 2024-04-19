package com.xxxjjsss.bookstore.dto.member;

import com.xxxjjsss.bookstore.domain.member.Member;
import com.xxxjjsss.bookstore.dto.book.BookResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class MemberResponseDto {

    private Long id;
    private String nickname;
    private List<BookResponseDto> books;

    public MemberResponseDto(Member member) {
        this.id = member.getId();
        this.nickname = member.getNickname();
        this.books = member.getBooks().stream()
                .map(book -> BookResponseDto.builder()
                        .book(book)
                        .build())
                .collect(Collectors.toList());
    }
}
