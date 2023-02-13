package com.devlon.fashionblog.entity;

import com.devlon.fashionblog.enums.Role;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users", uniqueConstraints = @UniqueConstraint(
        name = "email", columnNames = "email"
))
public class User extends AuditEntity {
    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private Long user_Id;
    @Column(name = "firstname", length = 200)
    private String firstName;
    @Column(name = "lastname", length = 200)
    private String lastName;
    @Column(name = "email", length = 200)
    private String email;
    @Column(name = "password", length = 200)
    private String password;
    @Enumerated(value = EnumType.STRING)
    @Builder.Default
    private Role role = Role.USER;

    @Column(name = "is_verified")
    @Builder.Default
    private Boolean isVerified = false;

    @Column(name = "locked")
    private Boolean isLocked = false;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private Set<BlogPost> posts = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Like> likes = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return user_Id != null && Objects.equals(user_Id, user.user_Id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
