package com.itstime.allpasstival.controller;

import com.itstime.allpasstival.domain.dto.UserResponse;
import com.itstime.allpasstival.domain.entity.User;
import com.itstime.allpasstival.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/users")
@Slf4j
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponse> userInfo(@PathVariable Integer id){
        Optional<User> optionalUser = userRepository.findById(id);
        UserResponse userResponse = UserResponse.builder()
                .id(optionalUser.get().getUserId())
                .nickname(optionalUser.get().getNickname())
                .profilPicUrl(optionalUser.get().getProfilPicUrl())
                .build();
        return ResponseEntity.ok().body(userResponse);
    }
}
