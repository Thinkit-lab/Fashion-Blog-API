package com.devlon.fashionblog.services.implementation;

import com.devlon.fashionblog.dto.BlogPostDto;
import com.devlon.fashionblog.dto.EditBlogPostDto;
import com.devlon.fashionblog.entity.BlogPost;
import com.devlon.fashionblog.entity.Category;
import com.devlon.fashionblog.entity.User;
import com.devlon.fashionblog.enums.Role;
import com.devlon.fashionblog.exception.BadRequestException;
import com.devlon.fashionblog.exception.NoContentException;
import com.devlon.fashionblog.exception.NotFoundException;
import com.devlon.fashionblog.repository.PostRepository;
import com.devlon.fashionblog.services.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserServiceImpl userService;
    private final CategoryServiceImpl categoryService;


    @Override
    public BlogPostDto createPost(BlogPostDto blogPostDto, Long userId) {
        BlogPost blogPost = new BlogPost();
        BeanUtils.copyProperties(blogPostDto, blogPost);

        User user = userService.getUserById(userId);
        Category category = categoryService.getCategoryByName(blogPostDto.getCategoryName());
        blogPost.setUser(user);
        blogPost.setCategory(category);
        blogPost.setCreatedBy(String.valueOf(Role.ADMIN));
        blogPost.setUpdatedBy(String.valueOf(Role.ADMIN));
        log.info("Creating post");
        BlogPost createdPost = postRepository.save(blogPost);

        return mapToPostDto(createdPost);
    }

    @Override
    public List<BlogPostDto> getAllPost() {
        List<BlogPost> blogPosts = postRepository.findAll();
        return blogPosts.stream().map(this::mapToPostDto).collect(Collectors.toList());
    }

    @Override
    public BlogPost getPostById(Long postId) {
        Optional<BlogPost> post = postRepository.findById(postId);
        if(post.isEmpty()) {
            throw new NotFoundException("Post not found");
        }
        return post.get();
    }

    @Override
    public void decreasePostLikeCount(Long postId) {
        try {
            postRepository.decreasePostLikeCount(postId);
        } catch (NoContentException e) {
            throw new NoContentException("Unable to complete the request");
        }
    }

    @Override
    public void increasePostLikeCount(Long postId) {
        try {
            postRepository.increasePostLikeCount(postId);
        } catch (NoContentException e) {
            throw new NoContentException("Unable to complete the request");
        }
    }

    @Override
    public void increaseCommentCount(Long postId) {
        try {
            postRepository.increasePostCommentCount(postId);
        } catch (NoContentException e) {
            throw new NoContentException("Unable to complete the request");
        }
    }

    @Override
    public int editPost(Long postId, EditBlogPostDto blogPostDto) {
        Optional<BlogPost> existingPost = postRepository.findById(postId);

        if(existingPost.isEmpty()) {
            throw new NotFoundException("Post not found");
        }
        try {
            log.info("Editing post");
            int edited = postRepository.updateBlogPost(blogPostDto.getPost_title(), blogPostDto.getPost_body(), postId);
            postRepository.updateUpdatedAt(LocalDateTime.now(), postId);
            return edited;
        } catch (BadRequestException e) {
            throw new BadRequestException("Bad request");
        }
    }

    @Override
    public void updateLikedPostStatus(Boolean status, Long postId) {
        try {
            postRepository.updateLikedPostStatus(status, postId);
        } catch (BadRequestException e) {
            throw new BadRequestException("Bad request");
        }
    }

    @Override
    public List<BlogPostDto> getPostByCategory(String categoryName) {
        List<BlogPost> blogPosts = postRepository.findAllByCategoryName(categoryName);

        if(blogPosts.isEmpty()) {
            throw new NotFoundException("Couldn't find any post with the category selected");
        }

        return blogPosts.stream().map(this::mapToPostDto).collect(Collectors.toList());
    }

    @Override
    public void deletePost(Long postId) {
        Optional<BlogPost> blogPost = postRepository.findById(postId);

        if(blogPost.isEmpty()) {
            throw new NotFoundException("Post not found");
        }
        try {
            postRepository.delete(blogPost.get());
        } catch (BadRequestException e) {
            throw new BadRequestException("Bad request");
        }
    }

    public BlogPostDto mapToPostDto(BlogPost blogPost) {
        return BlogPostDto.builder()
                .post_Id(blogPost.getPost_Id())
                .likedPost(blogPost.isLikedPost())
                .post_body(blogPost.getPost_body())
                .post_image(blogPost.getPost_image())
                .post_title(blogPost.getPost_title())
                .author(blogPost.getAuthor())
                .likeCount(blogPost.getLikeCount())
                .commentCount(blogPost.getCommentCount())
                .categoryName(blogPost.getCategoryName())
                .build();
    }
}
