package com.xxxjjsss.bookstore.service;

import com.xxxjjsss.bookstore.domain.Book;
import com.xxxjjsss.bookstore.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("bookId is invalid"));
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public void updateBook(Long bookId, Book bookDto) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("bookId is invalid"));
        book.update(bookDto);
    }

    public void deleteBookById(Book book) {
        bookRepository.delete(book);
    }
}
