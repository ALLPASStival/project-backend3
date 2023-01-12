package com.itstime.allpasstival.controller;

import com.itstime.allpasstival.domain.dto.*;
import com.itstime.allpasstival.domain.dto.user.*;
import com.itstime.allpasstival.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @GetMapping(value = "")
    public Response<UserInfoResponse> userInfo(Authentication authentication){
        UserInfoResponse userInfoResponse = userService.getUser(authentication.getName());
        return Response.success(userInfoResponse);
    }

    @PatchMapping("")
    public Response<UserUpdateResponse> updateUser(@RequestBody UserUpdateRequest userUpdateRequest, Authentication authentication){
        UserUpdateResponse userUpdateResponse = userService.updateUser(userUpdateRequest, authentication.getName());
        return Response.success(userUpdateResponse);

    }

    @GetMapping(value = "/email-exists/{email}")
    public Response<UserEmailExistResponse> isEmailExist(@PathVariable String email){
        UserEmailExistResponse userEmailExistResponse = userService.checkEmailExist(email);
        return Response.success(userEmailExistResponse);
    }

    @GetMapping(value = "/nickname-exists/{nickname}")
    public Response<UserNicknameExistResponse> isNicknameExist(@PathVariable String nickname){
        UserNicknameExistResponse userNicknameExistResponse = userService.checkNicknameExist(nickname);
        return Response.success(userNicknameExistResponse);
    }

//    @GetMapping ("/myposts")
//    public Response<Page<PostInfoResponse>> getMyPosts(@PageableDefault(size = 20, sort ="createdAt",
//            direction = Sort.Direction.DESC) Pageable pageable, Authentication authentication) {
//        List<PostInfoResponse> posts = postService.getMyPosts(pageable, authentication.getName());
//        return Response.success(new PageImpl<>(posts));
//    }



}
