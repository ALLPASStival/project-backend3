package com.itstime.allpasstival.domain.dto.comment;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class CommentInfoResponse {
    private Integer id;
    private String comment;
    private String userName;
    private Integer postId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd- hh:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd- hh:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime lastModifiedAt;

    public static CommentInfoResponse of(Comment comment){
        return CommentInfoResponse.builder()
                .id(comment.getId())
                .postId(comment.getPost().getPostId())
                .comment(comment.getComment())
                .userName(comment.getUser().getNickname())
                .createdAt(comment.getCreatedAt())
                .lastModifiedAt(comment.getLastModifiedAt())
                .build();
    }

}
