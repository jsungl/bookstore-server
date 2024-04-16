package com.xxxjjsss.bookstore;

import com.xxxjjsss.bookstore.repository.book.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	//@Bean
	@Profile("local")
	public TestDataInit testDataInit(BookRepository bookRepository) {
		return new TestDataInit(bookRepository);
	}

}
