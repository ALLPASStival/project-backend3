package com.itstime.allpasstival.domain.entity;

import javax.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Getter//클래스 내의 모든 getter 메소드 자동 생성
@NoArgsConstructor//기본생성자 자동 추가 Public Posts(){}같은거
@Entity //테이블과 링크될 클래스
public class festival {
    @Id//프라임키.축제 id,VARCHAR
    @GeneratedValue(strategy = GenerationType.IDENTITY)//프라임키 생성 규칙. 이걸 추가하면 자동생성된대요
    private int festivalID;

    @Column(length = 500)
    private String festival_name;//축제이름VARCHAR

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


    @Builder//빌드 패턴 클래스 생성
    public festival(String festival_name, String holding_venue,String start_date,String finish_date,String host_inst,String host_org,String tel_num,String homep_addr,String street_addr, int view,String etc, String author){
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

    public void update(String festival_name, String holding_venue, String start_date, String finish_date, String host_inst, String host_org, String tel_num, String homep_addr, String street_addr, int view, String etc) {
        this.festival_name = festival_name;
        this.holding_venue = holding_venue;
        this.start_date = start_date;
        this.finish_date = finish_date;
        this.host_inst = host_inst;
        this.host_org = host_org;
        this.tel_num = tel_num;
        this.homep_addr = homep_addr;
        this.street_addr = street_addr;
        this.view = view;
        this.etc = etc;
    }

    public festival orElseThrow(Object o) {
        return null;
    }
}
