package com.devlon.fashionblog.dto;

import com.devlon.fashionblog.entity.AuditEntity;
import com.devlon.fashionblog.entity.BlogPost;
import com.devlon.fashionblog.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto extends AuditEntity {
    private Long comment_id;
    @NotBlank(message = "Comment cannot be blank")
    @Length(max = 2147484)
    private String comment;
    private User user;
    private BlogPost post;
}
