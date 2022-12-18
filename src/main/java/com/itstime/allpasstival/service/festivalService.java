package com.itstime.allpasstival.service;


import com.itstime.allpasstival.domain.dto.festivalSaveRequestDto;
import com.itstime.allpasstival.domain.entity.festival;
import com.itstime.allpasstival.repository.festivalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class festivalService {

    private static final festivalRepository fesposRepository = null;

    @Transactional
    public int update(int festivalID, festivalSaveRequestDto requestDto){
        festivalRepository.findById(festivalID);

        festivalRepository.update(requestDto.getFestival_name(), requestDto.getHolding_venue(), requestDto.getStart_date(), requestDto.getFinish_date(), requestDto.getHost_inst(), requestDto.getHost_org(), requestDto.getTel_num(), requestDto.getHomep_addr(), requestDto.getStreet_addr(), requestDto.getView(), requestDto.getEtc());
        return festivalID;
    }

    public festivalSaveRequestDto findById(int festivalID){
        festivalRepository.findById(festivalID);
        return null;
    }

    //리스트 조회
    public static Page<festival> festivalList(Pageable pageable){
        return fesposRepository.findAll(pageable);

    }



    public festivalSaveRequestDto save(festivalSaveRequestDto requestDto) {
        return festivalSaveRequestDto.builder().festival_name().etc().finish_date().start_date().holding_venue();
    }


    //검색
    public Page<festival> searchFestival(String seach)






}
