package com.heyazoo1007.book.domain.posts;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.heyazoo1007.book.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter //lombok annotation
@NoArgsConstructor //lombok annotation
@Entity //JPA annotation
public class Posts extends BaseTimeEntity {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(length=500, nullable = false)
        private String bookTitle;

        @Column(columnDefinition = "TEXT",nullable = false)
        private String review;

        private String author;

        private String publisher;

        private String category;

        @Builder
        public Posts(String bookTitle, String author, String publisher, String category,String review){
            this.bookTitle=bookTitle;
            this.author=author;
            this.publisher=publisher;
            this.category=category;
            this.review=review;

        }


        public void update(String bookTitle,String author, String publisher, String category, String review){
            this.bookTitle=bookTitle;
            this.author=author;
            this.publisher=publisher;
            this.category=category;
            this.review=review;
        }
    }

