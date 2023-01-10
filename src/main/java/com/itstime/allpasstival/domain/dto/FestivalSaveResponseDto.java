package com.itstime.allpasstival.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor//모든 필드 값을 받는 생성자
@NoArgsConstructor//파라미터 없는 기본 생성자
@Getter//접근자
public class FestivalSaveResponseDto {
    private Integer festivalID;
    private String festivalName;//축제이름VARCHAR
    private String holdingVenue;//개최장소VARCHAR
    private String startDate;//시작일시DATE
    private String finishDate;//종료일시DATE
    private String hostInst;//주관기관VARCHAR
    private String hostOrg;//주최기관VARCHAR
    private String telNum;//전화번호VARCHAR
    private String homepAddr;//홈페이지 주소VARCHAR
    private String streetAddr;//도로명 주소VARCHAR
    private Integer view;//조회수
    private String etc;//비고TEXT
    private String author;
}
