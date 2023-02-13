package com.devlon.fashionblog.dto;

import com.devlon.fashionblog.entity.AuditEntity;
import com.devlon.fashionblog.entity.BlogPost;
import com.devlon.fashionblog.entity.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LikeDto extends AuditEntity {
    private long id;
    private BlogPost blogPost;
    private User user;
}
