package com.devlon.fashionblog.services.implementation;

import com.devlon.fashionblog.dto.BlogPostDto;
import com.devlon.fashionblog.dto.EditBlogPostDto;
import com.devlon.fashionblog.entity.BlogPost;
import com.devlon.fashionblog.entity.User;
import com.devlon.fashionblog.repository.PostRepository;
import com.devlon.fashionblog.repository.UserRepository;
import com.devlon.fashionblog.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostServiceImplTest {
    @MockBean
    private PostRepository postRepository;

    @Autowired
    private PostServiceImpl postService;
    private BlogPost post;

    @BeforeEach
    void setUp() {
        post = BlogPost.builder()
                .post_Id(1L)
                .post_body("Post Body")
                .post_image("eydgfghhhs")
                .post_title("Title")
                .author("Admin")
                .likedPost(false)
                .categoryName("Politics")
                .build();
        List<BlogPost> posts = new ArrayList<>(Arrays.asList(post));

        Mockito.when(postRepository.findAll()).thenReturn(posts);
        Mockito.when(postRepository.findById(1L)).thenReturn(Optional.ofNullable(post));
        Mockito.when(postRepository.updateBlogPost("Title", "Post Body", 1L)).thenReturn(1);
        Mockito.when(postRepository.findAllByCategoryName("Politics")).thenReturn(posts);
    }

    @Test
    void getAllPost() {
        List<BlogPostDto> postDtos = postService.getAllPost();
        assertNotNull(postDtos);
    }

    @Test
    void getPostById() {
        BlogPost postDto = postService.getPostById(1L);
        assertEquals(post.getPost_title(), postDto.getPost_title());
    }

    @Test
    void editPost() {
        EditBlogPostDto post = EditBlogPostDto.builder()
                .post_body("Post Body")
                .post_title("Title")
                .build();
        int blogPost = postService.editPost(1L, post);
        assert(blogPost!=0);
    }

    @Test
    void getPostByCategory() {
        List<BlogPostDto> posts = postService.getPostByCategory("Politics");
        assertNotNull(posts);
    }

    @Test
    void deletePost() {
    }
}