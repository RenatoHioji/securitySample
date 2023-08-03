package com.pb.week11.service.category;

import com.pb.week11.DTO.request.CategoryDTORequest;
import com.pb.week11.DTO.response.CategoryDTOResponse;
import com.pb.week11.entity.Category;
import com.pb.week11.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;
    @Override
    public CategoryDTOResponse save(CategoryDTORequest request) {
        return new CategoryDTOResponse(categoryRepository.save(new Category(request)));
    }

    @Override
    public List<CategoryDTOResponse> findAll() {
        return categoryRepository.findAll().stream().map(CategoryDTOResponse::new).toList();
    }
}
