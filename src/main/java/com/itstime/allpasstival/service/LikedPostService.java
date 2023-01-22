package com.itstime.allpasstival.service;

import com.itstime.allpasstival.domain.dto.post.PostLikeUpdateResponse;
import com.itstime.allpasstival.domain.entity.LikedPost;
import com.itstime.allpasstival.domain.entity.Post;
import com.itstime.allpasstival.domain.entity.User;
import com.itstime.allpasstival.repository.LikedPostRepository;
import com.itstime.allpasstival.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikedPostService {
    private final LikedPostRepository likedPostRepository;
    private final ValidateService validateService;
    private final PostRepository postRepository;

    public PostLikeUpdateResponse updateLike(Integer postId, String userId){
        User user = validateService.validateUser(userId);
        Post post = validateService.validatePost(postId);
        Optional<LikedPost> likedPost = likedPostRepository.findByPostAndUser(post,user);
        if(likedPost.isPresent()){
            validateService.validatePermission(likedPost.get().getUser(),user);
            likedPostRepository.delete(likedPost.get());
            post.changeLike(post.getLikes()-1);
            postRepository.save(post);
            return PostLikeUpdateResponse.builder()
                    .message("좋아요를 삭제했습니다.")
                    .build();
        }
        else{
            likedPostRepository.save(LikedPost.of(post,user));
            post.changeLike(post.getLikes()+1);
            postRepository.save(post);
            return PostLikeUpdateResponse.builder()
                    .message("좋아요를 눌렀습니다.")
                    .build();
        }
    }

    public Long countLike(Integer postId) {
        Post post = validateService.validatePost(postId);
        return likedPostRepository.countAllByPostId(postId);
    }


}
