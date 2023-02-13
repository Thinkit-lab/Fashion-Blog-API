package com.devlon.fashionblog.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "likes")
public class Like {
    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private long likeId;
    @ManyToOne
    @JoinColumn(name = "post_Id", referencedColumnName = "post_Id")
    private BlogPost blogPost;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_Id")
    private User user;

}
