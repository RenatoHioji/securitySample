package com.pb.week11.service.comment;

import com.pb.week11.DTO.request.CommentDTORequest;
import com.pb.week11.DTO.response.CommentDTOResponse;
import com.pb.week11.entity.Comment;
import com.pb.week11.entity.Posts;
import com.pb.week11.exception.ResourceNotFoundException;
import com.pb.week11.repository.CommentRepository;
import com.pb.week11.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostsRepository postsRepository;

    @Override
    public CommentDTOResponse saveComment(Long postId, CommentDTORequest request) {
        log.info("Procurando post by id");
        Posts postById = postsRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Post not founded by id %s.", postId)));
        return new CommentDTOResponse(commentRepository.save(new Comment(postById, request)));
    }

    @Override
    public List<CommentDTOResponse> findAllComment(Long postId) {
        return commentRepository.findByPostsId(postId).stream().map(CommentDTOResponse::new).toList();
    }
}
