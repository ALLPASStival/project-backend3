package com.itstime.allpasstival.domain.dto.comment;

import com.itstime.allpasstival.domain.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CommentDeleteResponse {
    private String message;
    private Integer id;

    public static CommentDeleteResponse of(Comment comment){
        return CommentDeleteResponse.builder()
                .id(comment.getId())
                .message("댓글 삭제 완료")
                .build();
    }
}
