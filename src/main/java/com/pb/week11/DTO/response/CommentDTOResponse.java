package com.pb.week11.DTO.response;

import com.pb.week11.entity.Comment;

public record CommentDTOResponse(
        Long id,
        String name,
        String email,
        String body,
        Long post_id
) {
    public CommentDTOResponse(Comment comment){
        this(comment.getId(), comment.getName(), comment.getEmail(), comment.getBody(), comment.getPosts().getId());
    }

}
