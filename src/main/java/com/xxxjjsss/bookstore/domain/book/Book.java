package com.xxxjjsss.bookstore.domain.book;

import com.xxxjjsss.bookstore.domain.BaseTimeEntity;
import com.xxxjjsss.bookstore.domain.member.Member;
import com.xxxjjsss.bookstore.dto.book.BookRequestDto;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED) //JPA는 Public 또는 Protected의 기본 생성자가 필수이다. 따라서 기본 생성자를 넣어주거나 @NoArgsConstructor 를 사용해준다
public class Book extends BaseTimeEntity {

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", nullable = false)
    private Member member;


    public void update(BookRequestDto book) {
        this.title = book.getTitle();
        this.description = book.getDescription();
        this.price = book.getPrice();
        this.imageUrl = book.getImageUrl();
    }

}
