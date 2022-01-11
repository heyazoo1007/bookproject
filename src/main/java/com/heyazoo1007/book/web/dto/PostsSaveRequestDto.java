package com.heyazoo1007.book.web.dto;

import com.heyazoo1007.book.domain.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {

    private String bookTitle;
    private String author;
    private String publisher;
    public int category;

    @Builder
    public PostsSaveRequestDto(String bookTitle, String author, String publisher, int category){

        this.bookTitle=bookTitle;
        this.author=author;
        this.publisher=publisher;
        this.category=category;
    }
    public Posts toEntity(){
        return Posts.builder()
                .bookTitle(bookTitle)
                .author(author)
                .publisher(publisher)
                .category(category)
                .build();
    }

}
