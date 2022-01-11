package com.heyazoo1007.book.web.dto;

import com.heyazoo1007.book.domain.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {

    private Long id;
    private String bookTitle;
    private String author;
    private int category;
    private String publisher;

    public PostsResponseDto(Posts entity){

        this.id= entity.getId();
        this.bookTitle=entity.getBookTitle();
        this.author=entity.getAuthor();
        this.category= entity.getCategory();
        this.publisher= entity.getPublisher();
    }
}
