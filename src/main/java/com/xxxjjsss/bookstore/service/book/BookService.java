package com.xxxjjsss.bookstore.service.book;

import com.xxxjjsss.bookstore.domain.book.Book;
import com.xxxjjsss.bookstore.domain.member.Member;
import com.xxxjjsss.bookstore.dto.book.BookRequestDto;
import com.xxxjjsss.bookstore.dto.book.BookResponseDto;
import com.xxxjjsss.bookstore.global.exception.ApiException;
import com.xxxjjsss.bookstore.global.exception.ErrorCode;
import com.xxxjjsss.bookstore.global.security.SecurityUser;
import com.xxxjjsss.bookstore.repository.book.BookRepository;
import com.xxxjjsss.bookstore.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {

    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    /**
     * 목록조회
     */
    public List<BookResponseDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(book -> BookResponseDto.builder()
                            .book(book)
                            .build())
                .collect(Collectors.toList());
    }

    /**
     * 단건 조회
     */
    public BookResponseDto getBookById(Long bookId) {
        //Book book = bookRepository.findById(bookId).orElseThrow(() -> new ApiException(ErrorCode.BOOK_NOT_FOUND));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new ApiException(ErrorCode.BOOK_NOT_FOUND, "Book was not found for parameters %d".formatted(bookId)));

        return BookResponseDto.builder().book(book).build();
    }

    /**
     * 등록
     */
    public BookResponseDto addBook(BookRequestDto bookDto, Member member) {

        if(member == null) {
            throw new ApiException(ErrorCode.USER_NOT_FOUND);
        }

        Book book = Book.builder()
                .title(bookDto.getTitle())
                .description(bookDto.getDescription())
                .imageUrl(bookDto.getImageUrl())
                .price(bookDto.getPrice())
                .member(member)
                .build();

        Book saved = bookRepository.save(book);
        return BookResponseDto.builder().book(saved).build();
    }

    /**
     * 수정
     */
    public BookResponseDto updateBook(Long bookId, BookRequestDto bookDto) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new ApiException(ErrorCode.BOOK_NOT_FOUND));
        book.update(bookDto);

        Book saved = bookRepository.save(book);
        return BookResponseDto.builder().book(saved).build();
    }


    /**
     * 삭제
     */
    public void deleteBookById(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new ApiException(ErrorCode.BOOK_NOT_FOUND));
        bookRepository.delete(book);
    }

    /**
     * 검색
     */
    public List<BookResponseDto> findBook(String keyword) {
        List<Book> books;

        if(StringUtils.hasText(keyword)) {
            books = bookRepository.findByTitleLikeIgnoreCase("%" + keyword + "%");
        } else {
            books = bookRepository.findAll();
        }

        return books.stream()
                .map(book -> BookResponseDto.builder()
                        .book(book)
                        .build())
                .collect(Collectors.toList());
    }

}
