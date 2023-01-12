package com.itstime.allpasstival.controller;

import com.itstime.allpasstival.domain.dto.*;
import com.itstime.allpasstival.domain.dto.auth.JoinRequest;
import com.itstime.allpasstival.domain.dto.auth.JoinResponse;
import com.itstime.allpasstival.domain.dto.user.UserDto;
import com.itstime.allpasstival.domain.dto.user.UserLoginRequest;
import com.itstime.allpasstival.domain.dto.user.UserLoginResponse;
import com.itstime.allpasstival.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final UserService userService;
    @PostMapping(value = "/register")
    public Response<JoinResponse> register(@RequestBody JoinRequest joinRequest){
        UserDto userDto = userService.register(joinRequest);
        return Response.success(new JoinResponse(userDto.getEmail(), userDto.getNickname()));
    }

    @PostMapping("/login")
    public Response<UserLoginResponse> login(@RequestBody UserLoginRequest userLoginRequest){
        String token = userService.login(userLoginRequest.getEmail(),userLoginRequest.getPassword());
        return Response.success(new UserLoginResponse(token));
    }
}
