package com.xxxjjsss.bookstore.repository;

import com.xxxjjsss.bookstore.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitleLikeIgnoreCase(String title);
}
