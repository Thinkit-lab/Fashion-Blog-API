package com.devlon.fashionblog.controller;

import com.devlon.fashionblog.dto.LikeDto;
import com.devlon.fashionblog.services.implementation.LikeServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/fashion-blog/")
@Log4j2
public class LikeController {
    private final LikeServiceImpl likeService;

    @Autowired
    public LikeController(LikeServiceImpl likeService) {
        this.likeService = likeService;
    }


    @PostMapping("/post/{postId}/like/{userId}")
    public ResponseEntity<LikeDto> createLike(@PathVariable Long postId, @PathVariable Long userId) {
        log.info("Endpoint has been hit");
        LikeDto response = likeService.createLike(postId, userId);
        log.info("Like created successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
