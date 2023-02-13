package com.devlon.fashionblog.repository;

import com.devlon.fashionblog.entity.BlogPost;
import com.devlon.fashionblog.entity.Category;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository extends JpaRepository<BlogPost, Long> {
    @Modifying
    @Transactional
    @Query(value = "update blog_post set like_count = (like_count - 1) where post_id = ?1",
    nativeQuery = true)
    void decreasePostLikeCount(Long postId);

    @Modifying
    @Transactional
    @Query(value = "update blog_post set like_count = (like_count + 1) where post_id = ?1",
            nativeQuery = true)
    void increasePostLikeCount(Long postId);

    @Modifying
    @Transactional
    @Query(value = "update blog_post set comment_count = (blog_post.comment_count + 1) where post_id = ?1",
            nativeQuery = true)
    void increasePostCommentCount(Long postId);

    @Modifying
    @Transactional
    @Query(value = "update blog_post set post_title = ?1, post_body = ?2 where post_id = ?3",
            nativeQuery = true)
    int updateBlogPost(String postTitle, String postBody, Long postId);

    @Modifying
    @Transactional
    @Query(value = "update blog_post set updated_at = ?1 where post_id = ?2",
            nativeQuery = true)
    void updateUpdatedAt(LocalDateTime now, Long postId);

    @Modifying
    @Transactional
    @Query(value = "update blog_post set liked_post = ?1 where post_id = ?2",
            nativeQuery = true)
    void updateLikedPostStatus(Boolean status, Long postId);

    @Query(value = "select * from blog_post where category = ?1",
    nativeQuery = true)
    List<BlogPost> findAllByCategoryName(String categoryName);

}
