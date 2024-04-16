package com.xxxjjsss.bookstore.repository.book;

import com.xxxjjsss.bookstore.domain.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitleLikeIgnoreCase(String title);
}
