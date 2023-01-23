package com.itstime.allpasstival.domain.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ResetPasswordResponse {
    private String email;
    private String message;
    private String title;
}
