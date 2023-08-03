package com.pb.week11.DTO.response;


import com.pb.week11.entity.Category;
import com.pb.week11.entity.Posts;

public record PostsDTOResponse(Long id, String title, String description, String content, Category category) {
    public PostsDTOResponse(Posts posts){
        this(posts.getId(), posts.getTitle(), posts.getDescription(), posts.getContent(), posts.getCategory());
    }

}
