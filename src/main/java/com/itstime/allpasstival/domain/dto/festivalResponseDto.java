package com.itstime.allpasstival.domain.dto;

import com.itstime.allpasstival.domain.entity.festival;
import lombok.Getter;

@Getter
public class festivalResponseDto {
    private int festivalID;
    private String festival_name;
    private String holding_venue;
    private String start_date;
    private String finish_date;
    private String host_inst;
    private String host_org;
    private String tel_num;
    private String homep_addr;
    private String street_addr;
    private int view;
    private String etc;
    private String author;



    public festivalResponseDto(festival entity){
        this.festivalID = entity.getFestivalID();
        this.festival_name=entity.getFestival_name();
        this.holding_venue=entity.getHolding_venue();
        this.start_date=entity.getStart_date();
        this.finish_date=entity.getFinish_date();
        this.host_inst= entity.getHost_inst();
        this.host_org= entity.getHost_org();
        this.tel_num=entity.getTel_num();
        this.homep_addr=entity.getHomep_addr();
        this.street_addr= entity.getStreet_addr();
        this.view= entity.getView();
        this.etc=entity.getEtc();
        this.author=entity.getAuthor();
    }
}
