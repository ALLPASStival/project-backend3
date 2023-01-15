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
public class CommentModifyResponse {
    Integer id;
    String comment;
    String userName;
    Integer postId;
    LocalDateTime createdAt;
    LocalDateTime lastModifiedAt;

    public static CommentModifyResponse of(Comment comment, LocalDateTime createdAt){
        return CommentModifyResponse.builder()
                .id(comment.getId())
                .comment(comment.getComment())
                .userName(comment.getUser().getNickname())
                .postId(comment.getPost().getPostId())
                .createdAt(createdAt)
                .lastModifiedAt(comment.getLastModifiedAt())
                .build();
    }
}
