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
public class FestivalReserveResponse {
    Integer festivalId;
    String message;
    public static FestivalReserveResponse of(Festival festival, String message){
        return FestivalReserveResponse.builder()
                .festivalId(festival.getFestivalId())
                .message(message)
                .build();
    }

}
