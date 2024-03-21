package com.xxxjjsss.bookstore.controller;

import com.xxxjjsss.bookstore.domain.Book;
import com.xxxjjsss.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@Slf4j
public class BookController {

    private final BookService bookService;

    /**
     * 목록조회
     */
    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    /**
     * 단건 조회
     */
    @GetMapping("/book/{bookId}")
    public Book getBook(@PathVariable Long bookId) {
        return bookService.getBookById(bookId);
    }

    /**
     * 등록
     */
    @PostMapping("/addBook")
    public void saveBook(@RequestBody Book book) {
        bookService.saveBook(book);
    }

    /**
     * 수정
     */
    @PutMapping("/update")
    public void updateBook(@RequestParam("bookId") Long bookId, @RequestBody Book book) {
        bookService.updateBook(bookId, book);
    }

    /**
     * 삭제
     */
    @DeleteMapping("/deleteBook/{bookId}")
    public void deleteBook(@PathVariable Long bookId) {
        Book book = bookService.getBookById(bookId);
        bookService.deleteBookById(book);
    }
}
