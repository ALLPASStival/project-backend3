package com.itstime.allpasstival.domain.dto.user;

import com.itstime.allpasstival.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserDeleteResponse {
    private String message;
    private Integer userId;
    public static UserDeleteResponse of(User user){
        return UserDeleteResponse.builder()
                .userId(user.getUserId())
                .message("유저 삭제 완료")
                .build();
    }
}
