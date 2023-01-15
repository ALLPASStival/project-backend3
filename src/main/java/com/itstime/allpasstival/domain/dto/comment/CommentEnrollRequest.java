package com.itstime.allpasstival.domain.dto.comment;

import com.itstime.allpasstival.domain.entity.Comment;
import com.itstime.allpasstival.domain.entity.Post;
import com.itstime.allpasstival.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CommentEnrollRequest {
    String comment;

    public Comment toEntity(Post post, User user){
        return Comment.builder()
                .comment(this.comment)
                .user(user)
                .post(post)
                .build();
    }
}
