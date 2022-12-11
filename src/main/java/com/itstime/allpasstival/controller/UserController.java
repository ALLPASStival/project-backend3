package com.itstime.allpasstival.controller;

import com.itstime.allpasstival.domain.dto.*;
import com.itstime.allpasstival.domain.entity.User;
import com.itstime.allpasstival.repository.UserRepository;
import com.itstime.allpasstival.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserInfoResponse> userInfo(@PathVariable Integer id){
        Optional<User> optionalUser = userRepository.findById(id);
        UserInfoResponse userInfoResponse = UserInfoResponse.builder()
                .email(optionalUser.get().getEmail())
                .nickname(optionalUser.get().getNickname())
                .profilPicUrl(optionalUser.get().getProfilPicUrl())
                .build();
        return ResponseEntity.ok().body(userInfoResponse);
    }

    @PostMapping(value = "/register")
    public Response<UserJoinResponse> register(@RequestBody UserJoinRequest userJoinRequest){
        UserDto userDto = userService.register(userJoinRequest);
        return Response.success(new UserJoinResponse(userDto.getEmail(), userDto.getNickname()));
    }

}
