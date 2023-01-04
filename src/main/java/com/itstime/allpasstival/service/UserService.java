package com.itstime.allpasstival.service;


import com.itstime.allpasstival.domain.dto.UserDto;
import com.itstime.allpasstival.domain.dto.JoinRequest;
import com.itstime.allpasstival.domain.dto.UserInfoResponse;
import com.itstime.allpasstival.domain.entity.User;
import com.itstime.allpasstival.exception.AllPasstivalAppException;
import com.itstime.allpasstival.exception.ErrorCode;
import com.itstime.allpasstival.repository.UserRepository;
import com.itstime.allpasstival.utils.JwtTokenUtil;
import io.swagger.models.auth.In;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;


    @Value("${jwt.token.secret}")
    private
    String secretKey;
    private long expireTimeMs = 1000 * 60 * 60;

    public void checkEmailExist(String email){
        userRepository.findByEmail(email)
                .ifPresent((user)->{ throw new AllPasstivalAppException(ErrorCode.DUPLICATED_USER_EMAIL,String.format("email <%s> is exist",email ));});
    }

    public void checkNicknameExist(String nickname){
        userRepository.findByNickname(nickname)
                .ifPresent((user)->{ throw new AllPasstivalAppException(ErrorCode.DUPLICATED_USER_NICKNAME,String.format("nickname <%s> is exist", nickname ));});

    }

    public UserDto register(JoinRequest request){
        this.checkEmailExist(request.getEmail());
        this.checkNicknameExist(request.getNickname());

        User savedUser = userRepository.save(request.toEntity(encoder.encode(request.getPassword())));
        return UserDto.builder()
                .userId(savedUser.getUserId())
                .email(savedUser.getEmail())
                .nickname(savedUser.getNickname())
                .build();
    }

    public String login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new AllPasstivalAppException(ErrorCode.NOT_FOUND, ErrorCode.NOT_FOUND.getMessage()));
        if(!encoder.matches(password,user.getPassword())){
            throw new AllPasstivalAppException(ErrorCode.INVALID_PASSWORD, ErrorCode.INVALID_PASSWORD.getMessage());
        }

        return JwtTokenUtil.createToken(user.getUserId(), secretKey, expireTimeMs);
    }

    public User getUserByUserId(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(()->new AllPasstivalAppException(ErrorCode.NOT_FOUND,ErrorCode.NOT_FOUND.getMessage()));
    }

    public UserInfoResponse getUser(String userId){
        User user = userRepository.findById(Integer.parseInt(userId))
                .orElseThrow(()-> new AllPasstivalAppException(ErrorCode.NOT_FOUND, ErrorCode.NOT_FOUND.getMessage()));
        return UserInfoResponse.builder()
                .email(user.getEmail())
                .profilePicUrl(user.getProfilePicUrl())
                .nickname(user.getNickname())
                .build();
    }

}
