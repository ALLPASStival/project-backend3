package com.itstime.allpasstival.domain.dto.festival;

import com.itstime.allpasstival.domain.entity.Festival;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class FestivalUpdateRequestDto {
    private String festivalName;//축제이름VARCHAR
    private String holdingVenue;//개최장소VARCHAR
    private Integer startDate;//시작일시DATE
    private Integer finishDate;//종료일시DATE
    private String hostInst;//주관기관VARCHAR
    private String hostOrg;//주최기관VARCHAR
    private String telNum;//전화번호VARCHAR
    private String homepAddr;//홈페이지 주소VARCHAR
    private String streetAddr;//도로명 주소VARCHAR
    private String etc;//비고TEXT

    public Festival toEntity(Festival festival){
        return Festival.builder().
                festivalId(festival.getFestivalId())
                .holdingVenue(festival.getHoldingVenue())
                .startDate(festival.getStartDate())
                .finishDate(festival.getFinishDate())
                .hostInst(festival.getHostInst())
                .hostOrg(festival.getHostOrg())
                .telNum(festival.getTelNum())
                .homepAddr(festival.getHomepAddr())
                .streetAddr(festival.getStreetAddr())
                .etc(festival.getEtc())
                .build();
    }

}
