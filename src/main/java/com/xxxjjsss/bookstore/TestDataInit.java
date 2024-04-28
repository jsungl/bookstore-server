package com.xxxjjsss.bookstore;

import com.xxxjjsss.bookstore.domain.book.Book;
import com.xxxjjsss.bookstore.repository.book.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@Slf4j
@RequiredArgsConstructor
public class TestDataInit {
    private final BookRepository bookRepository;

    /**
     * 초기 데이터 추가
     */
    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        log.info("Test data init!");
        bookRepository.save(Book.builder()
                .title("Java: Learn Java in One Day and Learn It Well. Java for Beginners with Hands-on Project. (Learn Coding Fast with Hands-On Project Book 4)")
                .description("Have you always wanted to learn computer programming but are afraid it'll be too difficult for you? Or perhaps you know other programming languages but are interested in learning the Java language fast?\n\nThis book is for you.")
                .price(17.0)
                .imageUrl("https://m.media-amazon.com/images/I/41Vt1zmwXjL._SY445_SX342_.jpg")
                .build());
        bookRepository.save(Book.builder()
                .title("The Road to React: Your journey to master plain yet pragmatic React.js")
                .description("\"The Road to React\" made its debut in 2016, and since then, I've almost rewritten it annually. This book teaches the core principles of React, guiding you through building a practical application in pure React without complex tooling. The book covers everything from setting up the project to deploying it on a server. Each chapter includes additional recommended reading and exercises. By the end, you'll have the skills to develop your own React applications.\n" +
                        "\n" +
                        "In \"The Road to React,\" I establish a solid foundation before delving into the broader React ecosystem. The book clarifies general concepts, patterns, and best practices for real-world React applications. Ultimately, you'll learn to construct a React application from scratch, incorporating features such as pagination, client-side and server-side searching, and advanced UI interactions like sorting.")
                .price(29.72)
                .imageUrl("https://m.media-amazon.com/images/I/51j1nrM7ETL._SY466_.jpg")
                .build());
        bookRepository.save(Book.builder()
                .title("C++ Programming Language, The 4th Edition")
                .description("The new C++11 standard allows programmers to express ideas more clearly, simply, and directly, and to write faster, more efficient code. Bjarne Stroustrup, the designer and original implementer of C++, has reorganized, extended, and completely rewritten his definitive reference and tutorial for programmers who want to use C++ most effectively.")
                .price(68.89)
                .imageUrl("https://m.media-amazon.com/images/I/71uN0nVAkvL._SY466_.jpg")
                .build());
        bookRepository.save(Book.builder()
                .title("Beginning Node.js, Express & MongoDB Development")
                .description("In this book, we take you on a fun, hands-on and pragmatic journey to learning Node.js, Express and MongoDB development. You'll start building your first Node.js app within minutes. Every chapter is written in a bite-sized manner and straight to the point as I don't want to waste your time (and most certainly mine) on the content you don't need. In the end, you will have the skills to create a blog app and deploy it to the Internet.")
                .price(16.13)
                .imageUrl("https://m.media-amazon.com/images/I/61w5eUdyIsL._SY466_.jpg")
                .build());
        bookRepository.save(Book.builder()
                .title("Learn JavaScript Quickly: A Complete Beginner’s Guide to Learning JavaScript, Even If You’re New to Programming (Crash Course With Hands-On Project)")
                .description("Become a Master Coder and Cash In the Big Bucks: JavaScript Programming Made Easy.")
                .price(9.90)
                .imageUrl("https://m.media-amazon.com/images/I/61HUuiDH+0L._SY466_.jpg")
                .build());
    }
}
