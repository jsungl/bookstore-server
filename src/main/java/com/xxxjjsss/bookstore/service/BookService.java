package com.xxxjjsss.bookstore.service;

import com.xxxjjsss.bookstore.domain.Book;
import com.xxxjjsss.bookstore.dto.BookRequestDto;
import com.xxxjjsss.bookstore.dto.BookResponseDto;
import com.xxxjjsss.bookstore.global.exception.ApiException;
import com.xxxjjsss.bookstore.global.exception.ErrorCode;
import com.xxxjjsss.bookstore.repository.BookRepository;
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

/*
    public BookResponseDto getBookById(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new ApiException(ErrorCode.BOOK_NOT_FOUND, "Can not find the requested resource. id=%d".formatted(bookId)));
        return BookResponseDto.builder().book(book).build();
    }
*/

/*
    public BookResponseDto getBookById(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new ApiException(ErrorCode.BOOK_NOT_FOUND, "Book was not found for parameters %d".formatted(bookId)));

        return BookResponseDto.builder().book(book).build();
    }
*/

    public BookResponseDto getBookById(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new ApiException(ErrorCode.BOOK_NOT_FOUND));

        return BookResponseDto.builder().book(book).build();
    }

    /**
     * 등록
     */
    public BookResponseDto addBook(BookRequestDto bookDto) {

        Book book = Book.builder()
                .title(bookDto.getTitle())
                .description(bookDto.getDescription())
                .imageUrl(bookDto.getImageUrl())
                .price(bookDto.getPrice())
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
