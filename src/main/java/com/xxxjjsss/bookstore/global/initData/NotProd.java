package com.xxxjjsss.bookstore.global.initData;

import com.xxxjjsss.bookstore.domain.member.Member;
import com.xxxjjsss.bookstore.dto.book.BookRequestDto;
import com.xxxjjsss.bookstore.dto.member.MemberRequestDto;
import com.xxxjjsss.bookstore.service.book.BookService;
import com.xxxjjsss.bookstore.service.member.MemberService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class NotProd {

    @Bean
    CommandLineRunner initData(BookService bookService, MemberService memberService) {
        MemberRequestDto memberRequestDto1 =
                new MemberRequestDto("codingtrain1", "qwer123!", "codingtrain1@naver.com", "train1");
        MemberRequestDto memberRequestDto2 =
                new MemberRequestDto("codingbrain1", "qwer123!", "codingbrain1@naver.com", "brain1");
        MemberRequestDto memberRequestDto3 =
                new MemberRequestDto("admin1", "qwer123!", "admin1@naver.com", "admin");

        BookRequestDto bookRequestDto1 =
                new BookRequestDto("Java: Learn Java in One Day and Learn It Well. Java for Beginners with Hands-on Project. (Learn Coding Fast with Hands-On Project Book 4)",
                        "Have you always wanted to learn computer programming but are afraid it'll be too difficult for you? Or perhaps you know other programming languages but are interested in learning the Java language fast?\n\nThis book is for you.",
                        "https://m.media-amazon.com/images/I/41Vt1zmwXjL._SY445_SX342_.jpg",
                        17.0
                        );
        BookRequestDto bookRequestDto2 =
                new BookRequestDto("The Road to React: Your journey to master plain yet pragmatic React.js",
                        "\"The Road to React\" made its debut in 2016, and since then, I've almost rewritten it annually. This book teaches the core principles of React, guiding you through building a practical application in pure React without complex tooling. The book covers everything from setting up the project to deploying it on a server. Each chapter includes additional recommended reading and exercises. By the end, you'll have the skills to develop your own React applications. \n\n" +
                                "In \"The Road to React,\" I establish a solid foundation before delving into the broader React ecosystem. The book clarifies general concepts, patterns, and best practices for real-world React applications. Ultimately, you'll learn to construct a React application from scratch, incorporating features such as pagination, client-side and server-side searching, and advanced UI interactions like sorting.",
                        "https://m.media-amazon.com/images/I/51j1nrM7ETL._SY466_.jpg",
                        29.72
                );
        BookRequestDto bookRequestDto3 =
                new BookRequestDto("C++ Programming Language, The 4th Edition",
                        "The new C++11 standard allows programmers to express ideas more clearly, simply, and directly, and to write faster, more efficient code. Bjarne Stroustrup, the designer and original implementer of C++, has reorganized, extended, and completely rewritten his definitive reference and tutorial for programmers who want to use C++ most effectively.",
                        "https://m.media-amazon.com/images/I/71uN0nVAkvL._SY466_.jpg",
                        68.89
                );

        return args -> {

            //회원 3명 추가
            Member user1 = memberService.join(memberRequestDto1);
            Member user2 = memberService.join(memberRequestDto2);
            Member user3 = memberService.join(memberRequestDto3);

            //책 등록
            bookService.addBook(bookRequestDto1, user1);
            bookService.addBook(bookRequestDto2, user1);
            bookService.addBook(bookRequestDto3, user2);

        };
    }
}
