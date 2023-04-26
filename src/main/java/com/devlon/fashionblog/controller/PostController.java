package com.devlon.fashionblog.controller;

import com.devlon.fashionblog.constants.ApplicationConstant;
import com.devlon.fashionblog.dto.BlogPostDto;
import com.devlon.fashionblog.dto.EditBlogPostDto;
import com.devlon.fashionblog.services.implementation.PostServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fashion-blog/")
@Log4j2
@RequiredArgsConstructor
public class PostController {
    private PostServiceImpl postService;

    @Autowired
    public PostController(PostServiceImpl postService) {
        this.postService = postService;
    }

    @PostMapping("/post/{userId}")
    public ResponseEntity<BlogPostDto> createPost(@RequestBody @Valid BlogPostDto blogPostDto, @PathVariable Long userId) {
        log.info(ApplicationConstant.ENDPOINT_HIT);
        BlogPostDto response = postService.createPost(blogPostDto, userId);
        log.info("Post created successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/post")
    public ResponseEntity<List<BlogPostDto>> showAllPost() {
        log.info(ApplicationConstant.ENDPOINT_HIT);
        List<BlogPostDto> response = postService.getAllPost();
        log.info("Posts generated successfully");
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @PatchMapping ("/post/{postId}")
    public ResponseEntity<?> editPost(@PathVariable Long postId, @RequestBody EditBlogPostDto blogPostDto) {
        log.info(ApplicationConstant.ENDPOINT_HIT);
        postService.editPost(postId, blogPostDto);
        log.info("Post edited successfully");
        return new ResponseEntity<>("Post edited Successfully", HttpStatus.CREATED);
    }

    @GetMapping("/post/category")
    public ResponseEntity<List<BlogPostDto>> showAllPostByCategory(@RequestParam String categoryName) {
        log.info(ApplicationConstant.ENDPOINT_HIT);
        List<BlogPostDto> response = postService.getPostByCategory(categoryName);
        log.info("Successfully");
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @DeleteMapping("/post/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId) {
        log.info(ApplicationConstant.ENDPOINT_HIT);
        postService.deletePost(postId);
        log.info("Successfully");
        return new ResponseEntity<>("Success", HttpStatus.FOUND);
    }
}
