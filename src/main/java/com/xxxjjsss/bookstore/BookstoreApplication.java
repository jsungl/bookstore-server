package com.xxxjjsss.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * This generated password is for development use only. Your security configuration must be updated before running your application in production.
 * 스프링 시큐리티 사용시 자동으로 제공되는 시큐리티 패스워드 대신 JWT를 사용하므로 UserDetailsServiceAutoConfiguration 클래스를 제외시킨다
 */
@SpringBootApplication(exclude= {UserDetailsServiceAutoConfiguration.class})
@EnableJpaAuditing
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

/*
	@Bean
	@Profile("dev")
	public TestDataInit testDataInit(BookRepository bookRepository) {
		return new TestDataInit(bookRepository);
	}
*/

}
