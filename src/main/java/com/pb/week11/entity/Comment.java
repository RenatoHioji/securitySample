package com.pb.week11.entity;

import com.pb.week11.DTO.request.CommentDTORequest;
import jakarta.persistence.*;
import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

@Entity
@Table(name="comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id", nullable = false)
    private Posts posts;

    public Comment(Posts post, CommentDTORequest request) {
        this.posts = post;
        this.name = request.name();
        this.email = request.email();
        this.body = request.name();
    }


}
