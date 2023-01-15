package com.itstime.allpasstival.domain.dto.festival;

import com.itstime.allpasstival.domain.entity.Festival;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FestivalUpdateResponseDto {
    private Integer festivalId;
    private String message;

    public static FestivalUpdateResponseDto of(Festival festival) {
        return FestivalUpdateResponseDto.builder()
                .festivalId(festival.getFestivalId())
                .message("포스트 수정 완료")
                .build();
    }
}
