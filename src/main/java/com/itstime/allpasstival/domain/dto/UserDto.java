package com.itstime.allpasstival.domain.dto;

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
    private String profilPicUrl;
    private boolean isAdmin;
    private String refreshToken;

}
