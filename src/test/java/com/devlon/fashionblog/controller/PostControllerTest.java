package com.devlon.fashionblog.controller;

import com.devlon.fashionblog.dto.BlogPostDto;
import com.devlon.fashionblog.dto.EditBlogPostDto;
import com.devlon.fashionblog.services.implementation.PostServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PostController.class)
class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostServiceImpl postService;
    private BlogPostDto postDto;
    List<BlogPostDto> blogPostDtos;

    @BeforeEach
    void setUp() {
        postDto = BlogPostDto.builder()
                .post_Id(1L)
                .post_body("Post Body")
                .post_image("eydgfghhhs")
                .post_title("Title")
                .author("Admin")
                .likedPost(false)
                .categoryName("Politics")
                .build();

        blogPostDtos = new ArrayList<>(Arrays.asList(postDto));
    }

    @Test
    void createPost() throws Exception {
        Mockito.when(postService.createPost(postDto, 1L)).thenReturn(postDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/fashion-blog/post/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"post_title\":\"Test Post\",\n" +
                        "    \"post_body\":\"This is a test post\",\n" +
                        "    \"post_image\":\"https://cdn.cookielaw.org/logos/ae0f0b5c-7045-4cca-a692-5dc9f2f50e0c/53293706-d137-416a-aad9-33d82ff81fc0/Post-Horn-Download.jpg\",\n" +
                        "    \"categoryName\":\"Entertainment\"}")).andExpect(status().isCreated());
    }

    @Test
    void showAllPost() throws Exception {
        Mockito.when(postService.getAllPost()).thenReturn(blogPostDtos);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/fashion-blog/post")
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(302, status);

    }

    @Test
    void editPost() throws Exception {
        EditBlogPostDto editBlogPostDto = EditBlogPostDto.builder()
                .post_Id(1L)
                .post_body("Post Body")
                .post_title("Title")
                .build();
        Mockito.when(postService.editPost(1L, editBlogPostDto)).thenReturn(1);

        mockMvc.perform(MockMvcRequestBuilders.patch("/api/v1/fashion-blog/post/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"post_title\":\"Edited post\",\n" +
                        "    \"post_body\":\"This is an edited post\"}")).andExpect(status().isCreated());

    }

    @Test
    void deletePost() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/fashion-blog/post/1"))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(302, status);
    }
}