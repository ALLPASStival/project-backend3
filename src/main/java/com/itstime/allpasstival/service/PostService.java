package com.itstime.allpasstival.service;

import com.itstime.allpasstival.domain.dto.post.*;
import com.itstime.allpasstival.domain.entity.Festival;
import com.itstime.allpasstival.domain.entity.Post;
import com.itstime.allpasstival.domain.entity.User;
import com.itstime.allpasstival.enums.PostCategory;
import com.itstime.allpasstival.enums.ResponseState;
import com.itstime.allpasstival.repository.FestivalRepository;
import com.itstime.allpasstival.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final ValidateService validateService;
    private final FestivalRepository festivalRepository;
    private final LikedPostService likedPostService;

    //게시글 단건조회
    public PostInfoResponse getPost(Integer id){
       Post post = validateService.validatePost(id);
       return PostInfoResponse.of(post);
    }

    //게시글 전체 조회
    public Page<PostInfoResponse> getAllPosts(Pageable pageable, String category){
        PostCategory postCategory = validateService.validatePostCategory(category);
        Page<Post> postPages = postRepository.findAllByCategory(pageable, postCategory);
        return postPages.map(PostInfoResponse::of);
    }

    //게시글 검색
    public Page<PostInfoResponse> searchPosts(Pageable pageable, String keyword, String searchCategory){
        Page<Post> postPages = new PageImpl<>(new ArrayList<>());
        switch (searchCategory) {
            case "articleContent":
                postPages = postRepository.findAllByArticleContentContains(pageable, keyword);
                break;
            case "title":
                postPages = postRepository.findAllByTitleContains(pageable, keyword);
                break;
            case "user":
                postPages = postRepository.findAllByUser_NicknameContains(pageable, keyword);
                break;
        }
        return postPages.map(PostInfoResponse::of);
    }
    //게시글 등록
    public PostEnrollResponse enrollPost(PostEnrollRequest request, String category, String userId){
        User user = validateService.validateUser(userId);
        Festival festival;
        if(category.equals("free")||category.equals("service")){
            festival=null;
        }
        else {
            festival=validateService.validateFestival(request.getFestivalName());
            if(category.equals("review")){
                festival.changeReview(festival.getReview()+1);
                festivalRepository.save(festival);
            }
        }
        PostCategory postCategory =  validateService.validatePostCategory(category);
        ResponseState responseState = postCategory.equals(PostCategory.service)? ResponseState.onGoing : null;
        Post savedPost = postRepository.save(request.toEntity(user,festival,postCategory,responseState));
        return PostEnrollResponse.of(savedPost);
    }

    //게시글 수정
    public PostModifyResponse modifyPost(Integer id, PostModifyRequest request, String userId){
        User user = validateService.validateUser(userId);
        Post post = validateService.validatePost(id);
        validateService.validatePermission(post.getUser(),user);
        Post modifiedPost = postRepository.save(request.toEntity(user,post));
        return PostModifyResponse.of(modifiedPost);
    }

    //게시글 삭제
    public PostDeleteResponse deletePost(Integer id, String userId){
        User user = validateService.validateUser(userId);
        Post post = validateService.validatePost(id);
        if(post.getCategory().equals(PostCategory.review)){
            post.getFestival().changeReview(post.getFestival().getReview()-1);
        }
        validateService.validatePermission(post.getUser(),user);
        postRepository.deleteById(id);
        return PostDeleteResponse.of(post);
    }

    //내 게시글 모아보기
    public Page<PostInfoResponse> getMyPosts(Pageable pageable, String userId) {
        User user = validateService.validateUser(userId);
        Page<Post> postPages = postRepository.findAllByUser(pageable, user);
        return postPages.map(PostInfoResponse::of);
    }

    //고객센터 답변 상태 변경하기
    public PostModifyResponse changeState(Integer postId, String state,  String userId) {
        Post post = validateService.validatePost(postId);
        User user = validateService.validateUser(userId);
        validateService.validateAdmin(user);
        ResponseState responseState = validateService.validateState(state);
        post.changeState(responseState);
        postRepository.save(post);
        return PostModifyResponse.of(post);
    }
}
