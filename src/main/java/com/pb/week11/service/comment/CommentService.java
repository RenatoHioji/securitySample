package com.pb.week11.service.comment;

import com.pb.week11.DTO.request.CommentDTORequest;
import com.pb.week11.DTO.response.CommentDTOResponse;

import java.util.List;

public interface CommentService {
    CommentDTOResponse saveComment(Long postId, CommentDTORequest request);

    List<CommentDTOResponse> findAllComment(Long postId);

}
