package com.pb.week11.service.category;

import com.pb.week11.DTO.request.CategoryDTORequest;
import com.pb.week11.DTO.response.CategoryDTOResponse;

import java.util.List;

public interface CategoryService {
    CategoryDTOResponse save(CategoryDTORequest request);
    List<CategoryDTOResponse> findAll();
}
