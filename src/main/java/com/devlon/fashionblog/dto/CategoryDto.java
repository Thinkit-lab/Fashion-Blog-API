package com.devlon.fashionblog.dto;

import com.devlon.fashionblog.entity.AuditEntity;
import com.devlon.fashionblog.entity.BlogPost;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto extends AuditEntity {
    private Long categoryId;
    @NotBlank(message = "Category name cannot be blank")
    private String name;
    private String description;
    private List<BlogPost> blogPosts;
}
