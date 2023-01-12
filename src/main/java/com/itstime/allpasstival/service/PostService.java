package com.itstime.allpasstival.service;

import com.itstime.allpasstival.domain.dto.*;
import com.itstime.allpasstival.domain.entity.Festival;
import com.itstime.allpasstival.domain.entity.Post;
import com.itstime.allpasstival.domain.entity.User;
import com.itstime.allpasstival.enums.PostCategory;
import com.itstime.allpasstival.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final ValidateService validateService;

    public PostInfoResponse getPost(Integer id){
        Post post = validateService.validatePost(id);
        return PostInfoResponse.of(post);
    }
//
//    public List<PostInfoResponse> getAllPosts(Pageable pageable){
//        Page<Post> postPages = postRepository.findAll(pageable);
//        List<Post> postLists = new ArrayList<>();
//        List<PostInfoResponse> postInfoResponses = new ArrayList<>();
//        if(postPages!=null && postPages.hasContent()){
//            postLists = postPages.getContent();
//        }
//        for (Post postList : postLists) {
//            postInfoResponses.add(PostInfoResponse.of(postList));
//        }
//        return postInfoResponses;
//    }
//
    public PostEnrollResponse enrollPost(PostEnrollRequest request, String category, String userId){
        User user = validateService.validateUser(userId);
        Festival festival = validateService.validateFestival(request.getFestivalId());
        PostCategory postCategory =  validateService.validatePostCategory(category);
        Post savedPost = postRepository.save(request.toEntity(user,festival,postCategory));
        return new PostEnrollResponse().of(savedPost);
    }
//
//    public PostModifyResponse modifyPost(Integer id, PostModifyRequest request, String userId){
//        User user = validateService.validateUser(userId);
//        Post post = validateService.validatePost(id);
//        validateService.validatePermission(post.getUser(),user);
//        Post modifiedPost = postRepository.save(request.toEntity(user,post));
//        return PostModifyResponse.of(modifiedPost);
//    }
//
//    public PostDeleteResponse deletePost(Integer id, String userId){
//        User user = validateService.validateUser(userId);
//        Post post = validateService.validatePost(id);
//        validateService.validatePermission(post.getUser(),user);
//        postRepository.deleteById(id);
//        return PostDeleteResponse.of(post);
//    }
//
//
//    public Page<PostInfoResponse> getMyPosts(Pageable pageable, String userId) {
//        User user = validateService.validateUser(userId);
//        Page<Post> postPages = postRepository.findAllByUser_UserId(pageable, Integer.parseInt(userId));
//        Page<PostInfoResponse> postInfoResponses = postPages.map(PostInfoResponse::of);
//        return postInfoResponses;
//    }
}