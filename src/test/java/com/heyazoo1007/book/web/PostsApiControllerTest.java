package com.heyazoo1007.book.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heyazoo1007.book.domain.posts.Posts;
import com.heyazoo1007.book.domain.posts.PostsRepository;
import com.heyazoo1007.book.web.dto.PostsSaveRequestDto;
import com.heyazoo1007.book.web.dto.PostsUpdateRequestDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup(){
        mvc= MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @After
    public void tearDown() throws Exception{
            postsRepository.deleteAll();
    }

    @Test
    @WithMockUser(roles = "USER")
    public void Post_등록된다() throws Exception{
        //given
        String bookTitle="버터";
        String author ="유즈키";
        String publisher="a 출판사";
        String category="문학";
        String review="후기";

        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .bookTitle(bookTitle)
                .author(author)
                .publisher(publisher)
                .category(category)
                .review(review)
                .build();

        String url ="http://localhost:"+port+"/api/v1/posts";

        //when
        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url,requestDto,Long.class);

        //then
        List<Posts> all= postsRepository.findAll();
        Posts obj =all.get(0);
        assertThat(obj.getAuthor()).isEqualTo(author);
        assertThat(obj.getBookTitle()).isEqualTo(bookTitle);
        assertThat(obj.getCategory()).isEqualTo(category);
        assertThat(obj.getPublisher()).isEqualTo(publisher);
        assertThat(obj.getReview()).isEqualTo(review);
    }

    @Test
    @WithMockUser(roles = "USER")
    public void post_수정된다() throws Exception{
        //given
        Posts savedPosts=postsRepository.save(Posts.builder()
                .bookTitle("title")
                .category("문")
                .publisher("publisher")
                .author("author")
                .review("어쩌구")
                .build());
        //수정할 내용들
        Long updateId= savedPosts.getId();
        String expectedTitle="title2";
        String expectedCategory="경제";
        String expectedPublisher="publisher2";
        String expectedAuthor="author2";
        String expectedReview="저쩌구";

        PostsUpdateRequestDto requestDto= PostsUpdateRequestDto.builder()
                .bookTitle(expectedTitle)
                .author(expectedAuthor)
                .category(expectedCategory)
                .publisher(expectedPublisher)
                .review(expectedReview)
                .build();
        String url ="http://localhost:"+port+"/api/v1/posts/"+updateId;

        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when

        mvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        //then

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getBookTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getAuthor()).isEqualTo(expectedAuthor);
        assertThat(all.get(0).getCategory()).isEqualTo(expectedCategory);
        assertThat(all.get(0).getPublisher()).isEqualTo(expectedPublisher);
        assertThat(all.get(0).getReview()).isEqualTo(expectedReview);

    }


}