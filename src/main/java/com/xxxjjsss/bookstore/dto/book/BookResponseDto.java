package com.xxxjjsss.bookstore.dto.book;

import com.xxxjjsss.bookstore.domain.book.Book;
import lombok.*;

import java.time.format.DateTimeFormatter;

//@Data
@Getter
@AllArgsConstructor
public class BookResponseDto {

    private Long bookId;
    private String title;
    private String description;
    private String imageUrl;
    private Double price;
    private String createdDate;
    private String modifiedDate;

    @Builder
    public BookResponseDto(Book book) {
        this.bookId = book.getBookId();
        this.title = book.getTitle();
        this.description = book.getDescription();
        this.imageUrl = book.getImageUrl();
        this.price = book.getPrice();
        this.createdDate = book.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.modifiedDate = book.getModifiedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

}
