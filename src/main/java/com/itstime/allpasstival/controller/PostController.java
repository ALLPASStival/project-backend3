package com.itstime.allpasstival.controller;

import com.itstime.allpasstival.domain.dto.post.PostEnrollRequest;
import com.itstime.allpasstival.domain.dto.post.PostEnrollResponse;
import com.itstime.allpasstival.domain.dto.post.PostInfoResponse;
import com.itstime.allpasstival.domain.dto.Response;
import com.itstime.allpasstival.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping ("/{id}")
    public Response<PostInfoResponse> getPost(@PathVariable Integer id){
        PostInfoResponse postInfoResponse = postService.getPost(id);
        return Response.success(postInfoResponse);
    }

    @PostMapping("/{category}")
    public Response<PostEnrollResponse> writePost(@PathVariable String category, @RequestBody PostEnrollRequest postEnrollRequest, Authentication authentication){
        PostEnrollResponse postEnrollResponse = postService.enrollPost(postEnrollRequest,category,authentication.getName());
        return Response.success(postEnrollResponse);
    }


}
