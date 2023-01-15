package com.itstime.allpasstival.domain.dto.post;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.itstime.allpasstival.domain.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostInfoResponse {
    private Integer postId;
    private String title;
    private String articleContent;
    private String imageUrl;
    private String category;
    private String state;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd- hh:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd- hh:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime lastModifiedAt;
    public static PostInfoResponse of(Post post) {
        return PostInfoResponse.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .articleContent(post.getArticleContent())
                .imageUrl(post.getImageUrl())
                .category(post.getCategory().name())
                .state(post.getState()==null?null:post.getState().getState())
                .createdAt(post.getCreatedAt())
                .lastModifiedAt(post.getLastModifiedAt())
                .build();

    }
}
