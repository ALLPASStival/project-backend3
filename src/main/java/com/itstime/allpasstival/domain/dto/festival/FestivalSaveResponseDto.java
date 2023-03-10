package com.itstime.allpasstival.domain.dto.festival;

import com.itstime.allpasstival.domain.entity.Festival;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor//모든 필드 값을 받는 생성자
@NoArgsConstructor//파라미터 없는 기본 생성자
@Getter//접근자
@Builder
public class FestivalSaveResponseDto {
    private Integer festivalId;
    private String message;

    public static FestivalSaveResponseDto of(Festival festival) {
        return FestivalSaveResponseDto.builder()
                .festivalId(festival.getFestivalId())
                .message("등록 완료되었습니다")
                .build();
    }
}
