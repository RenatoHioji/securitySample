package com.pb.week11.service.posts;

import com.pb.week11.DTO.request.PostsDTORequest;
import com.pb.week11.DTO.response.CategoryDTOResponse;
import com.pb.week11.DTO.response.PostsDTOResponse;
import com.pb.week11.entity.Category;
import com.pb.week11.entity.PageController;
import com.pb.week11.entity.Posts;
import com.pb.week11.exception.ResourceNotFoundException;
import com.pb.week11.repository.CategoryRepository;
import com.pb.week11.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class PostsServiceImpl implements PostsService {
    private final PostsRepository postsRepository;
    private final CategoryRepository categoryRepository;
    @Override
    public PostsDTOResponse savePosts(PostsDTORequest request) {
        Posts toSave = new Posts(request, categoryRepository.findById(request.categoryId()).orElseThrow(() -> new ResourceNotFoundException("Category not found by id: " + request.categoryId())));
        return new PostsDTOResponse(postsRepository.save(toSave));
    }

    @Override
    public PageController findAll(Integer pageNo, Integer pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Page<Posts> posts = postsRepository.findAll(PageRequest.of(pageNo, pageSize, sort));
        return new PageController(posts.getContent().stream().map(PostsDTOResponse::new).toList(), posts);

    }
}
