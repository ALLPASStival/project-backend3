package com.itstime.allpasstival.service;

import com.itstime.allpasstival.domain.entity.Festival;
import com.itstime.allpasstival.domain.entity.Post;
import com.itstime.allpasstival.domain.entity.User;
import com.itstime.allpasstival.enums.PostCategory;
import com.itstime.allpasstival.exception.AllPasstivalAppException;
import com.itstime.allpasstival.exception.ErrorCode;
import com.itstime.allpasstival.repository.FestivalRepository;
import com.itstime.allpasstival.repository.PostRepository;
import com.itstime.allpasstival.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidateService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    private final FestivalRepository festivalRepository;

    //유저 정보 유무 확인
    public User validateUser(String userId){
        return userRepository.findById(Integer.parseInt(userId))
                .orElseThrow(()-> new AllPasstivalAppException(ErrorCode.USERNAME_NOT_FOUND, ErrorCode.USERNAME_NOT_FOUND.getMessage()));
    }

    //게시글 정보 유무 확인
    public Post validatePost(Integer postId){
        return  postRepository.findById(postId)
                .orElseThrow(()-> new AllPasstivalAppException(ErrorCode.POST_NOT_FOUND, ErrorCode.POST_NOT_FOUND.getMessage()));
    }

    //축제 정보 유무 확인
    public Festival validateFestival(Integer festivalId){
        return festivalRepository.findById(festivalId)
                .orElseThrow(()-> new AllPasstivalAppException(ErrorCode.FESTIVAL_NOT_FOUND, ErrorCode.FESTIVAL_NOT_FOUND.getMessage()));
    }

    //게시글 카테고리 유무 확인
    public PostCategory validatePostCategory(String postCategory){
        for(PostCategory category : PostCategory.values()){
            if(category.getCategory().equals(postCategory)){
                return category;
            }
        }
        throw new AllPasstivalAppException(ErrorCode.CATEGORY_NOT_FOUND, ErrorCode.CATEGORY_NOT_FOUND.getMessage());
    }

    //권한 조회
    public void validatePermission(User author, User currentUser){
        if(!(currentUser.getUserId()==author.getUserId()||currentUser.isAdmin())){
            throw new AllPasstivalAppException(ErrorCode.INVALID_PERMISSION,ErrorCode.INVALID_PASSWORD.getMessage());
        }
    }

}