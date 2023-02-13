package com.devlon.fashionblog.services.implementation;

import com.devlon.fashionblog.dto.LikeDto;
import com.devlon.fashionblog.entity.BlogPost;
import com.devlon.fashionblog.entity.Like;
import com.devlon.fashionblog.entity.User;
import com.devlon.fashionblog.exception.BadRequestException;
import com.devlon.fashionblog.exception.NoContentException;
import com.devlon.fashionblog.repository.LikeRepository;
import com.devlon.fashionblog.services.LikeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class LikeServiceImpl implements LikeService {
    private final LikeRepository likeRepository;
    private final UserServiceImpl userService;
    private final PostServiceImpl postService;

    public LikeServiceImpl(LikeRepository likeRepository, UserServiceImpl userService, PostServiceImpl postService) {
        this.likeRepository = likeRepository;
        this.userService = userService;
        this.postService = postService;
    }

    @Override
    public LikeDto createLike(Long postId, Long userId) {
        BlogPost blogPost = postService.getPostById(postId);
        User user = userService.getUserById(userId);

        Like like = new Like();
        like.setUser(user);
        like.setBlogPost(blogPost);

        Like existingLike = likeRepository.findByPostIdAndUserId(postId, userId);
        if(existingLike != null) {
            try {
                log.info("Deleting like and reducing postLikeCount");
                likeRepository.delete(existingLike);
                postService.decreasePostLikeCount(postId);
                postService.updateLikedPostStatus(false, postId);
                log.info("Like successfully deleted");
                return null;
            } catch (NoContentException e) {
                throw new NoContentException("Couldn't complete request");
            }
        } else {
            try {
                log.info("Creating like");
                Like createdLike = likeRepository.save(like);
                postService.increasePostLikeCount(postId);
                postService.updateLikedPostStatus(true, postId);
                return mapToLikeDto(createdLike);
            } catch (BadRequestException e) {
                throw new BadRequestException("Couldn't complete request");
            }
        }
    }

    private LikeDto mapToLikeDto(Like createdLike) {
        return LikeDto.builder()
                .id(createdLike.getLikeId())
                .build();
    }
}
