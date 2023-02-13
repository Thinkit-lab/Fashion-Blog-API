package com.devlon.fashionblog.repository;

import com.devlon.fashionblog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
