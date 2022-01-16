package com.heyazoo1007.book.web.dto;

import com.heyazoo1007.book.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {

    private String bookTitle;
    private String author;
    private String publisher;
    public String category;
    private String review;

    @Builder
    public PostsSaveRequestDto(String bookTitle, String author, String publisher, String category, String review){

        this.bookTitle=bookTitle;
        this.author=author;
        this.publisher=publisher;
        this.category=category;
        this.review=review;
    }
    public Posts toEntity(){
        return Posts.builder()
                .bookTitle(bookTitle)
                .author(author)
                .publisher(publisher)
                .category(category)
                .review(review)
                .build();
    }

}
