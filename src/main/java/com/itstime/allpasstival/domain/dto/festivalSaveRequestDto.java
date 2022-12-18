package com.itstime.allpasstival.domain.dto;

import javax.persistence.Column;

import com.itstime.allpasstival.domain.entity.festival;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class festivalSaveRequestDto {

    private String festival_name;//축제이름VARCHAR
    private int festivalID;

    @Column(columnDefinition = "TEXT")//컬럼구간
    private String holding_venue;//개최장소VARCHAR
    private String start_date;//시작일시DATE
    private String finish_date;//종료일시DATE
    private String host_inst;//주관기관VARCHAR
    private String host_org;//주최기관VARCHAR
    private String tel_num;//전화번호VARCHAR
    private String homep_addr;//홈페이지 주소VARCHAR
    private String street_addr;//도로명 주소VARCHAR
    private int view;//조회수
    private String etc;//비고TEXT

    private String author;


    @Builder
    public festivalSaveRequestDto(String festival_name, String holding_venue,String start_date,String finish_date,String host_inst,String host_org,String tel_num,String homep_addr,String street_addr, int view,String etc, String author) {
        this.festival_name=festival_name;
        this.author=author;
        this.holding_venue=holding_venue;
        this.start_date=start_date;
        this.finish_date=finish_date;
        this.host_inst=host_inst;
        this.host_org=host_org;
        this.tel_num=tel_num;
        this.homep_addr=homep_addr;
        this.street_addr=street_addr;
        this.view=view;
        this.etc=etc;
    }
    public festival toEntity() {
        return festival.builder()
                .festival_name(festival_name)
                .holding_venue(holding_venue)
                .start_date(start_date)
                .finish_date(finish_date)
                .host_inst(host_inst)
                .host_org(host_org)
                .tel_num(tel_num)
                .homep_addr(homep_addr)
                .street_addr(street_addr)
                .view(view)
                .etc(etc)
                .author("allpasstival@gmail.com")//임의로 넣은거
                .build();
    }

}
