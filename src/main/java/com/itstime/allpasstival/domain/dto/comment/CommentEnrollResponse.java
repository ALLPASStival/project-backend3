package com.itstime.allpasstival.domain.dto.comment;

import com.itstime.allpasstival.domain.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CommentEnrollResponse {
    Integer id;
    String comment;
    String userName;
    Integer postId;
    LocalDateTime createdAt;

    public CommentEnrollResponse of(Comment comment){
        return CommentEnrollResponse.builder()
                .id(comment.getId())
                .comment(comment.getComment())
                .userName(comment.getUser().getNickname())
                .postId(comment.getPost().getPostId())
                .createdAt(comment.getCreatedAt())
                .build();
    }
}
