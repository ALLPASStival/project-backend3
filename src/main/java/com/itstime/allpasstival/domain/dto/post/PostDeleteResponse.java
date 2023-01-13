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
public class PostDeleteResponse {
    private String message;
    private Integer postId;
    public static PostDeleteResponse of(Post post) {
        return PostDeleteResponse.builder()
                .postId(post.getPostId())
                .message("포스트 삭제 완료")
                .build();
    }
}
