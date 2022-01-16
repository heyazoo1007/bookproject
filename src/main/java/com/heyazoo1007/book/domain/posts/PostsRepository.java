package com.heyazoo1007.book.domain.posts;

import com.heyazoo1007.book.domain.posts.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts,Long> {

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findALLDesc();
}
