package com.itstime.allpasstival.service;


import com.itstime.allpasstival.domain.dto.UserDto;
import com.itstime.allpasstival.domain.dto.UserJoinRequest;
import com.itstime.allpasstival.domain.entity.User;
import com.itstime.allpasstival.exception.AllPasstivalAppException;
import com.itstime.allpasstival.exception.ErrorCode;
import com.itstime.allpasstival.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;



    public void checkEmailExist(String email){
        userRepository.findByEmail(email)
                .ifPresent((user)->{ throw new AllPasstivalAppException(ErrorCode.DUPLICATED_USER_EMAIL,String.format("email <%s> is exist",email ));});
    }

    public void checkNicknameExist(String nickname){
        userRepository.findByNickname(nickname)
                .ifPresent((user)->{ throw new AllPasstivalAppException(ErrorCode.DUPLICATED_USER_NICKNAME,String.format("nickname <%s> is exist", nickname ));});

    }

    public UserDto register(UserJoinRequest request){
        this.checkEmailExist(request.getEmail());
        this.checkNicknameExist(request.getNickname());

        User savedUser = userRepository.save(request.toEntity(encoder.encode(request.getPassword())));
        return UserDto.builder()
                .userId(savedUser.getUserId())
                .email(savedUser.getEmail())
                .nickname(savedUser.getNickname())
                .build();
    }

}
