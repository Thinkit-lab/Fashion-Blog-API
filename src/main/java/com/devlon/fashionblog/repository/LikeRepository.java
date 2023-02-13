package com.devlon.fashionblog.repository;

import com.devlon.fashionblog.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LikeRepository extends JpaRepository<Like, Long> {
    @Query(value = "select * from likes where post_id = ?1 and user_id = ?2",
    nativeQuery = true)
    Like findByPostIdAndUserId(Long postId, Long userId);
}
