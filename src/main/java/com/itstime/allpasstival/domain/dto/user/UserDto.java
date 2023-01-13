package com.itstime.allpasstival.domain.dto.user;

import io.swagger.models.auth.In;
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
    private String gender;
    private Integer age;
    private String profilePicUrl;
    private boolean isAdmin;
    private String refreshToken;

}
