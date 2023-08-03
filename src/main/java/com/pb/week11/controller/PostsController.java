package com.pb.week11.controller;

import com.pb.week11.DTO.request.PostsDTORequest;
import com.pb.week11.DTO.response.PostsDTOResponse;
import com.pb.week11.entity.PageController;
import com.pb.week11.service.posts.PostsServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
@Tag(
        name="CRUD for Post Resouce"
)
public class PostsController {

        private final PostsServiceImpl postsService;

        @Operation(
                summary = "Create Post API",
                description = "Create Post API is used to save posts in the database"
        )
        @ApiResponse(
                responseCode = "201",
                description = "Http status 201 CREATED"
        )
        @SecurityRequirement(
                name="Bear Authentication"
        )
        @PreAuthorize("hasRole('ADMIN')")
        @PostMapping
        public ResponseEntity<PostsDTOResponse> savePosts(@Validated @RequestBody PostsDTORequest request, UriComponentsBuilder builder) {
            var response = postsService.savePosts(request);
            var uri = builder.path("/api").buildAndExpand(response.id()).toUri();
            return ResponseEntity.created(uri).body(response);
        }
        @GetMapping
        public ResponseEntity<PageController> findPosts(
            @RequestParam(value="pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value="pageSize", defaultValue = "5", required = false) int pageSize,
            @RequestParam(value="sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value="sortDir", defaultValue="desc", required=false) String sortDir){
        return ResponseEntity.ok(postsService.findAll(pageNo, pageSize, sortBy, sortDir));
    }
}
