package com.itstime.allpasstival.service;

import com.itstime.allpasstival.domain.dto.comment.CommentEnrollRequest;
import com.itstime.allpasstival.domain.dto.comment.CommentEnrollResponse;
import com.itstime.allpasstival.domain.dto.comment.CommentInfoResponse;
import com.itstime.allpasstival.domain.entity.Comment;
import com.itstime.allpasstival.domain.entity.Post;
import com.itstime.allpasstival.domain.entity.User;
import com.itstime.allpasstival.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ValidateService validateService;

    public CommentEnrollResponse enrollComment(Integer postId, CommentEnrollRequest request, String userId){
        User user = validateService.validateUser(userId);
        Post post = validateService.validatePost(postId);
        Comment comment = commentRepository.save(request.toEntity(post,user));
        return new CommentEnrollResponse().of(comment);
    }

    public Page<CommentInfoResponse> getComments(Integer postId, Pageable pageable) {
        Post post = validateService.validatePost(postId);
        Page<Comment> commentPages = commentRepository.findAllByPost(post,pageable);
        return commentPages.map(CommentInfoResponse::of);
    }
}