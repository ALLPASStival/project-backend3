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
public class PostModifyRequest {
    private String title;
    private String articleContent;
    private String imageUrl;
    public Post toEntity(User user, Post post) {
        return Post.builder()
                .postId(post.getPostId())
                .articleContent(this.articleContent)
                .category(post.getCategory())
                .imageUrl(this.imageUrl)
                .likedPosts(post.getLikedPosts())
                .comments(post.getComments())
                .title(this.title)
                .user(user)
                .festival(post.getFestival())
                .build();
    }
}
