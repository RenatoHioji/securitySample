package com.pb.week11.DTO.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(
        description = "PostDTORequest model"
)
public record PostsDTORequest(
        @Schema(
                description = "Posts title"
        )
        @NotBlank(message="The title should not be blank.")
        @Size(min=5, message="Title should have at least 5 characters.")
        String title,
        @Schema(
                description = "Posts description"
        )
        @NotBlank(message="The description should not be blank.")
        @Size(min=10, message="Description should have at least 10 characters.")
        String description,
        @Schema(
                description = "Posts content"
        )
        @NotBlank(message="The content should not be blank.")
        @Size(min=3, message="Content should have at least 3 characters.")
        String content,

        @Schema(
                description = "Posts' category's id"
        )
        Long categoryId
) {
}
