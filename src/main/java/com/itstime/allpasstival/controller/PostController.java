package com.itstime.allpasstival.controller;

import com.itstime.allpasstival.domain.dto.post.*;
import com.itstime.allpasstival.domain.dto.Response;
import com.itstime.allpasstival.service.LikedPostService;
import com.itstime.allpasstival.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final LikedPostService likedPostService;

    //게시글 상세 조회
    @GetMapping ("/{id}")
    public Response<PostInfoResponse> getPost(@PathVariable Integer id){
        PostInfoResponse postInfoResponse = postService.getPost(id);
        return Response.success(postInfoResponse);
    }

    //게시글 등록
    @PostMapping("/{category}")
    public Response<PostEnrollResponse> writePost(@PathVariable String category, @RequestBody PostEnrollRequest postEnrollRequest, Authentication authentication){
        PostEnrollResponse postEnrollResponse = postService.enrollPost(postEnrollRequest,category,authentication.getName());
        return Response.success(postEnrollResponse);
    }

    //게시글 전체 조회
    @GetMapping("")
    public Response<Page<PostInfoResponse>> getAllPosts(@PageableDefault(size = 20, sort ="createdAt",
            direction = Sort.Direction.DESC) Pageable pageable) {
        Page<PostInfoResponse> posts = postService.getAllPosts(pageable);
        return Response.success(posts);
    }

    //게시글 수정
    @PutMapping("/{id}")
    public Response<PostModifyResponse> modifyPost(@PathVariable Integer id, @RequestBody PostModifyRequest postModifyRequest, Authentication authentication){
        PostModifyResponse postModifyResponse = postService.modifyPost(id, postModifyRequest, authentication.getName());
        return Response.success(postModifyResponse);

    }

    //게시글 삭제
    @DeleteMapping("/{id}")
    public Response<PostDeleteResponse> deletePost(@PathVariable Integer id, Authentication authentication){
        PostDeleteResponse postDeleteResponse = postService.deletePost(id,authentication.getName());
        return Response.success(postDeleteResponse);

    }

    //좋아요 개수 확인
    @GetMapping ("/{id}/likes")
    public Response<Long> CountLike(@PathVariable Integer id) {
        return Response.success(likedPostService.countLike(id));
    }

    //좋아요 누르기/취소하기
    @PostMapping("/{id}/likes")
    public Response<PostLikeUpdateResponse> AddLike(@PathVariable Integer id, Authentication authentication){
        PostLikeUpdateResponse likeUpdateResponse = likedPostService.updateLike(id, authentication.getName());
        return Response.success(likeUpdateResponse);
    }


}
