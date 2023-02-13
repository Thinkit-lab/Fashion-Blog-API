package com.devlon.fashionblog.services.implementation;

import com.devlon.fashionblog.dto.BlogPostDto;
import com.devlon.fashionblog.dto.CommentDto;
import com.devlon.fashionblog.dto.UserDto;
import com.devlon.fashionblog.entity.BlogPost;
import com.devlon.fashionblog.entity.Comment;
import com.devlon.fashionblog.entity.User;
import com.devlon.fashionblog.exception.BadRequestException;
import com.devlon.fashionblog.exception.NotFoundException;
import com.devlon.fashionblog.repository.CommentRepository;
import com.devlon.fashionblog.repository.UserRepository;
import com.devlon.fashionblog.services.CommentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
public class CommentServiceImpl implements CommentService {
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final PostServiceImpl postService;
    private final UserServiceImpl userService;

    public CommentServiceImpl(CommentRepository commentRepository, PostServiceImpl postService, UserServiceImpl userService,
                              UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postService = postService;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Override
    public CommentDto createComment(CommentDto commentDto, Long postId, Long userId) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDto, comment);
        BlogPost post = postService.getPostById(postId);
        User user = userService.getUserById(userId);

        comment.setPost(post);
        comment.setUser(user);

        try {
            Comment createdComment = commentRepository.save(comment);
            postService.increaseCommentCount(postId);
            return mapToCommentDto(createdComment);
        } catch (BadRequestException e) {
            throw new BadRequestException("Bad request");
        }
    }

    @Override
    public List<CommentDto> getAllComment() {
        List<Comment> comments = commentRepository.findAll();

        if(comments.isEmpty()) {
            throw new NotFoundException("No comment found");
        }
        return comments.stream().map(this::mapToCommentDto).collect(Collectors.toList());
    }

    @Override
    public void deleteComment(Long commentId) {
        Optional<Comment> comment = commentRepository.findById(commentId);

        if(comment.isEmpty()) {
            throw new NotFoundException("Comment not found");
        }
        try {
            commentRepository.delete(comment.get());
        } catch (BadRequestException e) {
            throw new BadRequestException("Bad request");
        }
    }

    public CommentDto mapToCommentDto(Comment comment) {
        return CommentDto.builder()
                .comment_id(comment.getComment_id())
                .comment(comment.getComment())
                .build();
    }
}
