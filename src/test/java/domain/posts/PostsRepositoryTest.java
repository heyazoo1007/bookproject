package domain.posts;

import com.heyazoo1007.book.domain.posts.Posts;
import com.heyazoo1007.book.domain.posts.PostsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){

        //given
        String bookTitle = "책제목";

        postsRepository.save(Posts.builder()
                .bookTitle(bookTitle)


                .author("heyazoo1007@naver.com")
                .category("문학")
                .publisher("publisher")
                .review("review")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getBookTitle()).isEqualTo(bookTitle);

    }

    @Test
    public void BaseTimeEntity_등록(){
        //given
        LocalDateTime now =LocalDateTime.of(2019,6,4,0,0,0);
        postsRepository.save(Posts.builder()
                .author("author")
                .publisher("publisher")
                .category("문학")
                .bookTitle("title")
                .review("review")
                .build());

        //when
        List<Posts> postsList =postsRepository.findAll();

        //then
        Posts post= postsList.get(0);

        System.out.println(">>>>>> createdDate="+post.getCreatedDate()+",modifiedDate="+post.getModifiedDate());

        assertThat(post.getCreatedDate()).isAfter(now);
        assertThat(post.getModifiedDate()).isAfter(now);
    }
}
