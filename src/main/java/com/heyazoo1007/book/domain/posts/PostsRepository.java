package com.heyazoo1007.book.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;

public interface PostsRepository extends JpaRepository<Posts,Long> {

    @Query("SELECT p FROM Posts p ORDER BY p.id ASC")
    List<Posts> findALLAsc();


    /*@Query("SELECT p.review FROM Posts where id=p.id")
    Posts findReviewById(@Param("id") Long id);*/






}
