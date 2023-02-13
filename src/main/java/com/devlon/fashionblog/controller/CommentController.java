package com.devlon.fashionblog.controller;

import com.devlon.fashionblog.dto.CommentDto;
import com.devlon.fashionblog.services.implementation.CommentServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/fashion-blog/")
@Log4j2
public class CommentController {
    private final CommentServiceImpl commentService;

    @Autowired
    public CommentController(CommentServiceImpl commentService) {
        this.commentService = commentService;
    }


    @PostMapping("/post/{postId}/comment/{userId}")
    public ResponseEntity<CommentDto> createComment(@RequestBody @Valid CommentDto commentDto,
                                                    @PathVariable Long postId, @PathVariable Long userId) {
        log.info("Endpoint has been hit");
        CommentDto response = commentService.createComment(commentDto, postId, userId);
        log.info("Comment created successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/post/comment")
    public ResponseEntity<?> allComment() {
        log.info("Endpoint has been hit");
        List<CommentDto> response = commentService.getAllComment();
        log.info("Successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/post/comment/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId) {
        log.info("Endpoint has been hit");
        commentService.deleteComment(commentId);
        log.info("Successfully");
        return new ResponseEntity<>("Success", HttpStatus.GONE);
    }
}
