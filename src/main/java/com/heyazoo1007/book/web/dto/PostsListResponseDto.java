package com.heyazoo1007.book.web.dto;

import com.heyazoo1007.book.domain.posts.Posts;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostsListResponseDto {
    private Long id;
    private String bookTitle;
    private String author;
    private String publisher;
    private String category;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;


    public PostsListResponseDto(Posts entity){
        this.id= entity.getId();
        this.bookTitle= entity.getBookTitle();
        this.author=entity.getAuthor();
        this.publisher= entity.getPublisher();
        this.category= entity.getCategory();
        this.createdDate=entity.getCreatedDate();
        this.modifiedDate=entity.getModifiedDate();


    }




}
