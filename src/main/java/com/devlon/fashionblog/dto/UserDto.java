package com.devlon.fashionblog.dto;

import com.devlon.fashionblog.entity.AuditEntity;
import com.devlon.fashionblog.entity.BlogPost;
import com.devlon.fashionblog.entity.Comment;
import com.devlon.fashionblog.entity.Like;
import com.devlon.fashionblog.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
public class UserDto extends AuditEntity {
    private Long user_Id;
    @NotBlank(message = "Firstname cannot be blank")
    private String firstName;
    @NotBlank(message = "Lastname cannot be blank")
    private String lastName;
    @NotBlank(message = "Email cannot be blank")
    @Email
    private String email;
    @NotBlank(message = "Password cannot be blank")
    @Length(min = 3, message = "Password too short")
    private String password;
    private Role role = Role.USER;
    private Boolean isVerified = false;
    private Boolean isLocked = false;
    private Set<BlogPost> posts = new HashSet<>();
    private Set<Comment> comments = new HashSet<>();
    private List<Like> like = new ArrayList<>();
}
