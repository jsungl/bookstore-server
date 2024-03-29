package com.xxxjjsss.bookstore.dto;

import com.xxxjjsss.bookstore.domain.Book;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

//@Data
@Getter
@NoArgsConstructor
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
