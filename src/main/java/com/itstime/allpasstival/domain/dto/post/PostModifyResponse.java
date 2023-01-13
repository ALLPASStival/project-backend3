package com.itstime.allpasstival.domain.dto.post;

import com.itstime.allpasstival.domain.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostModifyResponse {
    private String message;
    private Integer postId;
    public static PostModifyResponse of(Post post) {
        return PostModifyResponse.builder()
                .postId(post.getPostId())
                .message("포스트 수정 완료")
                .build();
    }
}
