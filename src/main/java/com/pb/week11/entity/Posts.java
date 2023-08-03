package com.pb.week11.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pb.week11.DTO.request.PostsDTORequest;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@JsonIgnoreProperties("comments")
@Table(name="posts", uniqueConstraints = {@UniqueConstraint(columnNames={"title"})})
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="title")
    private String title;
    @Column(name="description")
    private String description;
    @Column(name="content")
    private String content;

    @OneToMany(mappedBy="posts", cascade = CascadeType.ALL)
    private Set<Comment> comments = new HashSet<>();

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="category_id", nullable = false)
    @JsonBackReference
    private Category category;




    public Posts(PostsDTORequest request, Category categories) {
        this.content = request.content();
        this.description = request.content();
        this.title = request.title();
        this.category = categories;
        categories.getPosts().add(this);
    }


}
