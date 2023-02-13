package com.devlon.fashionblog.dto;

import com.devlon.fashionblog.entity.AuditEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EditBlogPostDto extends AuditEntity {
    private Long post_Id;
    @NotBlank(message = "Title cannot be blank")
    private String post_title;
    @NotBlank(message = "Post cannot be blank")
    @Length(max = 2147484)
    private String post_body;
}
