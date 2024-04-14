package com.xxxjjsss.bookstore.controller;

import com.xxxjjsss.bookstore.dto.BookRequestDto;
import com.xxxjjsss.bookstore.dto.BookResponseDto;
import com.xxxjjsss.bookstore.global.RsData.ApiResponse;
import com.xxxjjsss.bookstore.service.BookService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    /**
     * 목록조회
     */
    @Getter
    @AllArgsConstructor
    public static class BooksResponse {
        private final List<BookResponseDto> books;
    }


    @GetMapping
    public ApiResponse<BooksResponse> getAllBooks(@RequestParam(value = "query", required = false) String query) {

        //요청 파라미터가 있다면 검색 서비스(기본검색 - 제목)
        if(StringUtils.hasText(query)) {
            List<BookResponseDto> result = bookService.findBook(query);
            return ApiResponse.success(new BooksResponse(result));
        }

        List<BookResponseDto> result = bookService.getAllBooks();
        return ApiResponse.success(new BooksResponse(result));

    }

    /**
     * 단건 조회
     */
    @Getter
    @AllArgsConstructor
    public static class BookResponse {
        private final BookResponseDto book;
    }

    @GetMapping("/{bookId}")
    public ApiResponse<BookResponse> getBook(@PathVariable Long bookId) {
        BookResponseDto result = bookService.getBookById(bookId);
        return ApiResponse.success(new BookResponse(result));
    }

    /**
     * 등록
     *
     * HTTP POST /books API를 호출하면 컨트롤러는 201 응답 코드를 반환한다.
     * HTTP 응답 상태 코드 201은 서버에서 리소스가 생성되었음을 나타낸다.
     */
    @PostMapping
    public ApiResponse<BookResponse> addBook(@Valid @RequestBody BookRequestDto bookDto) {
        BookResponseDto result = bookService.addBook(bookDto);
        return ApiResponse.success(new BookResponse(result));
    }

    /**
     * 수정
     */
    @PutMapping("/{bookId}")
    public ApiResponse<BookResponse> updateBook(@PathVariable Long bookId, @Valid @RequestBody BookRequestDto bookDto) {
        BookResponseDto result = bookService.updateBook(bookId, bookDto);
        return ApiResponse.success(new BookResponse(result));
    }

    /**
     * 삭제
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{bookId}")
    public void deleteBook(@PathVariable Long bookId) {
        bookService.deleteBookById(bookId);
    }

/*
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{bookId}")
    public ApiResponse<Void> deleteBook(@PathVariable Long bookId) {
        bookService.deleteBookById(bookId);
        return ApiResponse.success(null);
    }
*/

}
