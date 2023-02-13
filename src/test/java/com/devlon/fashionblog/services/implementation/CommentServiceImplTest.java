package com.devlon.fashionblog.services.implementation;

import com.devlon.fashionblog.dto.CommentDto;
import com.devlon.fashionblog.entity.Comment;
import com.devlon.fashionblog.repository.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentServiceImplTest {

    @Autowired
    private CommentServiceImpl commentService;
    @MockBean
    private CommentRepository commentRepository;
    private Comment comment;

    @BeforeEach
    void setUp() {
        comment = Comment.builder()
                .comment_id(1L)
                .comment("This is a comment")
                .build();
        List<Comment> comments = new ArrayList<>(Arrays.asList(comment));

        Mockito.when(commentRepository.findAll()).thenReturn(comments);
//        Mockito.when(commentRepository.delete(comment)).getMock();
    }

    @Test
    void getAllComment() {
        List<CommentDto> comments = commentService.getAllComment();
        assertNotNull(comments);
    }

    @Test
    void deleteComment() {
    }

}