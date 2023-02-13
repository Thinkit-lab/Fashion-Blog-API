package com.devlon.fashionblog.entity;

import com.devlon.fashionblog.enums.Role;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogPost extends AuditEntity{
    @Id
    @SequenceGenerator(name = "post_sequence", sequenceName = "post_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_sequence")
    private Long post_Id;
    @Column(name = "postTitle")
    private String post_title;
    @Column(name = "postBody")
    private String post_body;
    @Column(name = "postImage")
    private String post_image;
    @Column(name = "author")
    @Builder.Default
    private String author = String.valueOf(Role.ADMIN);
    @Column(name = "likeCount")
    private int likeCount;
    @Column(name = "commentCount")
    private int commentCount;
    @Column(name = "likedPost")
    @Builder.Default
    private boolean likedPost = false;
    @Column(name = "category")
    private String categoryName;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_Id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private Set<Comment> commentList = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "categoryId", referencedColumnName = "categoryId")
    private Category category;

    @OneToMany(mappedBy = "blogPost")
    @ToString.Exclude
    private List<Like> likes = new ArrayList<>();


}
