package com.itstime.allpasstival.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserDto {
    private Integer userId;
    private String email;
    private String nickname;
    private String password;
    private String profilePicUrl;
    private boolean isAdmin;
    private String refreshToken;

}
