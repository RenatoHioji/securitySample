package com.pb.week11.DTO.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CommentDTORequest (

        @NotBlank(message="The postId should not be blank.")
        @Size(min=5, message="Post id should have at least 5 characters.")
        Long post_id,
        @NotBlank(message="The email should not be blank.")
        @Size(min=5, message="Email should have at least 5 characters.")
        String email,
        @NotBlank(message="The name should not be blank.")
        @Size(min=5, message="Name should have at least 5 characters.")
        String name,
        @NotBlank(message="The body should not be blank.")
        @Size(min=5, message="Body should have at least 5 characters.")
        String body

) {
}
