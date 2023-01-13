package com.itstime.allpasstival.service;


import com.itstime.allpasstival.domain.dto.auth.JoinRequest;
import com.itstime.allpasstival.domain.dto.user.*;
import com.itstime.allpasstival.domain.entity.User;
import com.itstime.allpasstival.exception.AllPasstivalAppException;
import com.itstime.allpasstival.exception.ErrorCode;
import com.itstime.allpasstival.repository.UserRepository;
import com.itstime.allpasstival.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final ValidateService validateService;


    @Value("${jwt.token.secret}")
    private
    String secretKey;
    private long expireTimeMs = 1000 * 60 * 60;
    public UserEmailExistResponse checkEmailExist(String email){
        userRepository.findByEmail(email)
                .ifPresent((user)->{ throw new AllPasstivalAppException(ErrorCode.DUPLICATED_USER_EMAIL, ErrorCode.DUPLICATED_USER_EMAIL.getMessage());});
        return UserEmailExistResponse.builder()
                .isExist(false)
                .build();
    }

    public UserNicknameExistResponse checkNicknameExist(String nickname){
        userRepository.findByNickname(nickname)
                .ifPresent((user)->{ throw new AllPasstivalAppException(ErrorCode.DUPLICATED_USER_NICKNAME,ErrorCode.DUPLICATED_USER_NICKNAME.getMessage());});
        return UserNicknameExistResponse.builder()
                .isExist(false)
                .build();
    }

    //회원가입
    public UserDto register(JoinRequest request){
        this.checkEmailExist(request.getEmail());
        this.checkNicknameExist(request.getNickname());

        User savedUser = userRepository.save(request.toEntity(encoder.encode(request.getPassword())));
        return UserDto.builder()
                .userId(savedUser.getUserId())
                .gender(savedUser.getGender())
                .email(savedUser.getEmail())
                .age(savedUser.getAge())
                .nickname(savedUser.getNickname())
                .build();
    }

    //로그인
    public String login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new AllPasstivalAppException(ErrorCode.NOT_FOUND, ErrorCode.NOT_FOUND.getMessage()));
        if(!encoder.matches(password,user.getPassword())){
            throw new AllPasstivalAppException(ErrorCode.INVALID_PASSWORD, ErrorCode.INVALID_PASSWORD.getMessage());
        }

        return JwtTokenUtil.createToken(user.getUserId(), secretKey, expireTimeMs);
    }

    //유저id로 유저 정보 조회
    public User getUserByUserId(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(()->new AllPasstivalAppException(ErrorCode.NOT_FOUND,ErrorCode.NOT_FOUND.getMessage()));
    }

    //유저 정보 조회
    public UserInfoResponse getUser(String userId){
        User user = userRepository.findById(Integer.parseInt(userId))
                .orElseThrow(()-> new AllPasstivalAppException(ErrorCode.NOT_FOUND, ErrorCode.NOT_FOUND.getMessage()));
        return UserInfoResponse.builder()
                .email(user.getEmail())
                .age(user.getAge())
                .profilePicUrl(user.getProfilePicUrl())
                .nickname(user.getNickname())
                .gender(user.getGender())
                .build();
    }

    //유저 정보 수정
    public UserUpdateResponse updateUser(UserUpdateRequest request, String userId) {
        User beforeUser = userRepository.findById(Integer.parseInt(userId))
                .orElseThrow(()-> new AllPasstivalAppException(ErrorCode.NOT_FOUND, ErrorCode.NOT_FOUND.getMessage()));
        User updatedUser = User.builder()
                .UserId(beforeUser.getUserId())
                .profilePicUrl(request.getProfilePicUrl()==null? beforeUser.getProfilePicUrl() : request.getProfilePicUrl())
                .nickname(request.getNickname()==null? beforeUser.getNickname() : request.getNickname())
                .password(request.getPassword()==null? beforeUser.getPassword() : encoder.encode(request.getPassword()))
                .email(beforeUser.getEmail())
                .gender(request.getGender()==null?beforeUser.getGender():request.getGender())
                .posts(beforeUser.getPosts())
                .age(request.getAge()==null?beforeUser.getAge(): request.getAge())
                .reservedFestivals(beforeUser.getReservedFestivals())
                .recentlyViewedFestivals(beforeUser.getRecentlyViewedFestivals())
                .isAdmin(beforeUser.isAdmin())
                .build();
        userRepository.save(updatedUser);
        return UserUpdateResponse.builder()
                .email(updatedUser.getEmail())
                .profilePicUrl(updatedUser.getProfilePicUrl())
                .nickname(updatedUser.getNickname())
                .gender(updatedUser.getGender())
                .age(updatedUser.getAge())
                .build();
    }

    //유저 탈퇴
    public UserDeleteResponse deleteUser(String userId){
        User user = validateService.validateUser(userId);
        userRepository.deleteById(user.getUserId());
        return UserDeleteResponse.of(user);
    }
}
