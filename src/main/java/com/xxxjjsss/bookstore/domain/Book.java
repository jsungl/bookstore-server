package com.xxxjjsss.bookstore.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED) //JPA는 Public 또는 Protected의 기본 생성자가 필수이다. 따라서 기본 생성자를 넣어주거나 @NoArgsConstructor 를 사용해준다
public class Book extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "LONGTEXT")
    private String description;

    private String imageUrl;

    @Column(nullable = false)
    private Double price;


    public void update(Book book) {
        this.title = book.getTitle();
        this.description = book.getDescription();
        this.price = book.getPrice();
        this.imageUrl = book.getImageUrl();
    }

}
