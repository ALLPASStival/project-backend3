package com.itstime.allpasstival.domain.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Getter//클래스 내의 모든 getter 메소드 자동 생성
@NoArgsConstructor//기본생성자 자동 추가 Public Posts(){}같은거
@Entity //테이블과 링크될 클래스
@Builder
@AllArgsConstructor
public class Festival {
    @Id//프라임키.축제 id,VARCHAR
    @GeneratedValue(strategy = GenerationType.IDENTITY)//프라임키 생성 규칙. 이걸 추가하면 자동생성된대요
    private Integer festivalId;
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

    public static Festival of(ReservedFestival reservedFestival) {
        return reservedFestival.getFestival();
    }


}
