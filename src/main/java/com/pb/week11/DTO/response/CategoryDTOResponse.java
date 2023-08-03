package com.pb.week11.DTO.response;

import com.pb.week11.entity.Category;

public record CategoryDTOResponse(Long id, String name, String description) {
    public CategoryDTOResponse(Category category){
        this(category.getId(), category.getName(), category.getDescription());
    }
}
