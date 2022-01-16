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
    private String category;
    private String review;

    @Builder
    public PostsUpdateRequestDto(String bookTitle,String author, String publisher, String category,String review){
        this.bookTitle=bookTitle;
        this.author=author;
        this.publisher=publisher;
        this.category=category;
        this.review=review;
    }
}
