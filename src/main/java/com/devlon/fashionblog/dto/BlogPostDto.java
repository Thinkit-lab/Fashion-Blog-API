package com.devlon.fashionblog.dto;

import com.devlon.fashionblog.entity.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogPostDto extends AuditEntity {
    private Long post_Id;
    @NotBlank(message = "Title cannot be blank")
    private String post_title;
    @NotBlank(message = "Post cannot be blank")
    @Length(max = 2147484)
    private String post_body;
    private String post_image;
    private String author = "Admin";
    private int likeCount;
    private int commentCount;
    private boolean likedPost = false;
    private String categoryName;
    private User user;
    private Set<Comment> commentList = new HashSet<>();
    private Category category;
    private List<Like> likes = new ArrayList<>();

}
