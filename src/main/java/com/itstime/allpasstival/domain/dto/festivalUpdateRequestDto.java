package com.itstime.allpasstival.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class festivalUpdateRequestDto {
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


    @Builder
    public festivalUpdateRequestDto(String festival_name, String holding_venue, String start_date, String finish_date, String host_inst, String host_org, String tel_num, String homep_addr, String street_addr, int view, String etc) {
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
}
