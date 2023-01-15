package com.itstime.allpasstival.domain.dto.festival;

import com.itstime.allpasstival.domain.dto.post.PostDeleteResponse;
import com.itstime.allpasstival.domain.entity.Festival;
import com.itstime.allpasstival.domain.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FestivalDeleteResponse {
    private String message;
    private Integer festivalId;
    public static FestivalDeleteResponse of(Festival festival) {
        return FestivalDeleteResponse.builder()
                .festivalId(festival.getFestivalId())
                .message("포스트 삭제 완료")
                .build();
    }
}