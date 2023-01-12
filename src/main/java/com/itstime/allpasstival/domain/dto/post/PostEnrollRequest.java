package com.itstime.allpasstival.domain.dto.post;

import com.itstime.allpasstival.domain.entity.Festival;
import com.itstime.allpasstival.domain.entity.Post;
import com.itstime.allpasstival.domain.entity.User;
import com.itstime.allpasstival.enums.PostCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostEnrollRequest {
    private String title;
    private String articleContent;
    private String imageUrl;
    private Integer festivalId;
    public Post toEntity(User user, Festival festival, PostCategory category) {
        return Post.builder()
                .articleContent(this.articleContent)
                .category(category)
                .imageUrl(this.imageUrl)
                .title(this.title)
                .user(user)
                .festival(festival)
                .build();
    }

}