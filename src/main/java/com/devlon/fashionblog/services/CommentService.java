package com.devlon.fashionblog.services;

import com.devlon.fashionblog.dto.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto, Long postId, Long userId);

    List<CommentDto> getAllComment();

    void deleteComment(Long commentId);
}
