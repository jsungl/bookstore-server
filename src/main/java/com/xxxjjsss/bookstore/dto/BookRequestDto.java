package com.xxxjjsss.bookstore.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Data
public class BookRequestDto {

    private String title;
    private String description;
    private String imageUrl;
    private Double price;

}
