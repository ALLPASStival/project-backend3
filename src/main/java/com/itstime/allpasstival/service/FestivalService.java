package com.itstime.allpasstival.service;


import com.itstime.allpasstival.domain.dto.*;
import com.itstime.allpasstival.domain.entity.Festival;
import com.itstime.allpasstival.repository.FestivalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FestivalService {

    private final FestivalRepository fesposRepository;




    //리스트 조회
    public static Page<Festival> fesList(Pageable pageable){

        return null;
    }

    public static FestivalUpdateRequestDto findByid(Integer id) {

        return FestivalUpdateRequestDto.builder().build();
    }


    //글 작성
    public Integer save(FestivalSaveRequestDto requestDto) {
        return fesposRepository.save(requestDto.toEntity()).getFestivalID();
    }


    //리스트에서 게시글 세부조회. 게시글의 id를 받아와서 반환
    public FestivalDetailResponseDto viewDetail(Integer id){
        Festival festival = fesposRepository.findById(id).get();
        return FestivalDetailResponseDto.builder().
                holdingVenue(festival.getHoldingVenue()).
                hostInst(festival.getHostInst()).
                telNum(festival.getTelNum()).
                festivalName(festival.getFestivalName()).
                hostOrg(festival.getHostOrg()).
                etc(festival.getEtc()).
                view(festival.getView()).
                finishDate(festival.getFinishDate()).
                startDate(festival.getStartDate()).
                homepAddr(festival.getHomepAddr()).
                streetAddr(festival.getStreetAddr()).
                author(festival.getAuthor()).
                build();
    }

    //검색기능
    ///public Page<Festival> festivalSearch(String keyWord, Pageable pageable){
       /// return fesposRepository.findByKeyWordContaining(keyWord,pageable);
    ///}


    //게시글 삭제하는거
    //게시글 아이디 받아서 삭제
    public void Delete(Integer id){

        fesposRepository.deleteById((id));
    }

}
