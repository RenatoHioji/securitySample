package com.pb.week11.service.posts;


import com.pb.week11.DTO.request.PostsDTORequest;
import com.pb.week11.DTO.response.PostsDTOResponse;
import com.pb.week11.entity.PageController;

public interface PostsService {
    PostsDTOResponse savePosts(PostsDTORequest request);

    PageController findAll(Integer pageNo, Integer pageSize, String sortBy, String sortDir);
}
