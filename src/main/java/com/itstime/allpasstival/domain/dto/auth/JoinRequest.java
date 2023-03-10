package com.itstime.allpasstival.domain.dto.auth;

import com.itstime.allpasstival.domain.entity.User;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class JoinRequest {
    private String email;
    private String password;
    private String nickname;
    private String gender;
    private Integer age;

    public User toEntity(String hashedPassword){
        return User.builder()
                .email(this.email)
                .password(hashedPassword)
                .nickname(this.nickname)
                .gender(this.gender)
                .age(this.age)
                .build();
    }
}
