package com.xxxjjsss.bookstore.repository;

import com.xxxjjsss.bookstore.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book, Long> {

}
