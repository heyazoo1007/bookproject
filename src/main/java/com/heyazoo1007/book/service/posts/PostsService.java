package com.heyazoo1007.book.service.posts;

import com.heyazoo1007.book.domain.Posts;
import com.heyazoo1007.book.domain.PostsRepository;
import com.heyazoo1007.book.web.dto.PostsResponseDto;
import com.heyazoo1007.book.web.dto.PostsSaveRequestDto;
import com.heyazoo1007.book.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){

        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        posts.update(requestDto.getBookTitle(),requestDto.getAuthor(), requestDto.getPublisher(), requestDto.getCategory());
        return id;
    }

    public PostsResponseDto findById(Long id){
        Posts entity= postsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다 id="+id));
        return new PostsResponseDto(entity);
    }
}
