package com.pb.week11.controller;

import com.pb.week11.DTO.request.CategoryDTORequest;
import com.pb.week11.DTO.response.CategoryDTOResponse;
import com.pb.week11.service.category.CategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryServiceImpl categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDTOResponse>> findAll(){
        return ResponseEntity.ok(categoryService.findAll());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<CategoryDTOResponse> save(@RequestBody CategoryDTORequest request, UriComponentsBuilder builder){
        var uri = builder.buildAndExpand(categoryService.save(request).id()).toUri();
        return ResponseEntity.created(uri).body(categoryService.save(request));
    }
}
