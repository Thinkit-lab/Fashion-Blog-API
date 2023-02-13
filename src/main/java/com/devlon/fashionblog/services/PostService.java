package com.devlon.fashionblog.services;

import com.devlon.fashionblog.dto.BlogPostDto;
import com.devlon.fashionblog.dto.EditBlogPostDto;
import com.devlon.fashionblog.entity.BlogPost;

import java.util.List;

public interface PostService {
    BlogPostDto createPost(BlogPostDto blogPostDto, Long userId);

    List<BlogPostDto> getAllPost();

    BlogPost getPostById(Long postId);

    void decreasePostLikeCount(Long postId);

    void increasePostLikeCount(Long postId);

    void increaseCommentCount(Long postId);

    int editPost(Long postId, EditBlogPostDto blogPostDto);

    void updateLikedPostStatus(Boolean status, Long postId);

    List<BlogPostDto> getPostByCategory(String categoryName);

    void deletePost(Long postId);
}
