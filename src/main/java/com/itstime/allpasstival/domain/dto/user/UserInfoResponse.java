package com.itstime.allpasstival.domain.dto.user;

import com.itstime.allpasstival.domain.entity.User;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoResponse {
    private String email;
    private String nickname;
    private String profilePicUrl;
    private String gender;
    private Integer age;

}
