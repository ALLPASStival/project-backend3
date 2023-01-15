package com.itstime.allpasstival.controller;

import com.itstime.allpasstival.domain.dto.Response;
import com.itstime.allpasstival.domain.dto.comment.*;
import com.itstime.allpasstival.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts/{postId}")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    //댓글 조회
    @GetMapping("/comments")
    public Response<Page<CommentInfoResponse>> getComments(@PathVariable Integer postId, @PageableDefault(size = 10, sort ="createdAt",
            direction = Sort.Direction.DESC) Pageable pageable) {
        Page<CommentInfoResponse> comments = commentService.getComments(postId, pageable);
        return Response.success(comments);
    }

    //댓글 작성
    @PostMapping("/comments")
    public Response<CommentEnrollResponse> writeComment(@PathVariable Integer postId, @RequestBody CommentEnrollRequest commentEnrollRequest, Authentication authentication){
        CommentEnrollResponse commentEnrollResponse = commentService.enrollComment(postId,commentEnrollRequest,authentication.getName());
        return Response.success(commentEnrollResponse);
    }

    //댓글 수정
    @PutMapping("/comments/{id}")
    public Response<CommentModifyResponse> modifyComment(@PathVariable Integer postId, @PathVariable Integer id, @RequestBody CommentModifyRequest commentModifyRequest, Authentication authentication){
        CommentModifyResponse commentModifyResponse = commentService.modifyComment(postId,id,commentModifyRequest,authentication.getName());
        return Response.success(commentModifyResponse);
    }
}
