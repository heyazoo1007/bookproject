package com.heyazoo1007.book.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto {

    private String bookTitle;
    private String author;
    private String publisher;
    private int category;

    @Builder
    public PostsUpdateRequestDto(String bookTitle,String author, String publisher, int category){
        this.bookTitle=bookTitle;
        this.author=author;
        this.publisher=publisher;
        this.category=category;
    }
}
