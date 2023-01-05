package com.itstime.allpasstival.domain.dto;

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

}
