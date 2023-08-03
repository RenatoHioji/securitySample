package com.pb.week11.controller;

import com.pb.week11.DTO.request.CommentDTORequest;
import com.pb.week11.DTO.response.CommentDTOResponse;
import com.pb.week11.service.comment.CommentServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/{postId}/comment")
public class CommentController {
    private final CommentServiceImpl commentService;

    @PostMapping
    public ResponseEntity<CommentDTOResponse> saveComment(
            @Validated
            @PathVariable(value="postId") Long postId,
            @RequestBody CommentDTORequest request, UriComponentsBuilder builder){

        log.info("Criando Response");
        var response = commentService.saveComment(postId, request);
        log.info("Response Criado");
        var uri = builder.path("/api").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    public ResponseEntity<List<CommentDTOResponse>> findAllComments(@PathVariable(value="postId") Long postId){
        return ResponseEntity.ok(commentService.findAllComment(postId));
    }
}
