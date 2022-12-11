package com.itstime.allpasstival.domain.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Integer id;
    private String nickname;
    private String profilPicUrl;

}
