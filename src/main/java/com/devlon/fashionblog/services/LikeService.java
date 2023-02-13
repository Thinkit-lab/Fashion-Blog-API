package com.devlon.fashionblog.services;

import com.devlon.fashionblog.dto.LikeDto;

public interface LikeService {
    LikeDto createLike(Long postId, Long userId);
}
