package com.itstime.allpasstival.domain.dto;

import com.itstime.allpasstival.domain.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostEnrollResponse {
    private String message = "포스트 등록 완료";
    private Integer postId;
    public PostEnrollResponse of(Post post) {
        return PostEnrollResponse.builder()
                .postId(post.getPostId())
                .message(this.message)
                .build();
    }

}
