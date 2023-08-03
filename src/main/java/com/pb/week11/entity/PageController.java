package com.pb.week11.entity;

import com.pb.week11.DTO.response.PostsDTOResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class PageController {
    private List<PostsDTOResponse> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
    private Sort sortBy;
    public PageController(List<PostsDTOResponse> list, Page<Posts> postsList) {
        this.content = list;
        this.pageNo = postsList.getNumber();
        this.pageSize = postsList.getSize();
        this.totalElements = postsList.getTotalElements();
        this.totalPages = postsList.getTotalPages();
        this.last = postsList.isLast();
        this.sortBy = postsList.getSort();
    }
}
