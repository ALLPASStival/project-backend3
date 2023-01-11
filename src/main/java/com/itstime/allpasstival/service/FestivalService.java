package com.itstime.allpasstival.service;


import com.itstime.allpasstival.domain.dto.FestivalSaveRequestDto;
import com.itstime.allpasstival.domain.dto.FestivalUpdateRequestDto;
import com.itstime.allpasstival.domain.entity.Festival;
import com.itstime.allpasstival.repository.FestivalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FestivalService {

    private static final FestivalRepository fesposRepository = null;




    //리스트 조회
    public static Page<Festival> fesList(Pageable pageable){

        return fesposRepository.findAll(pageable);
    }

    public static FestivalUpdateRequestDto findByid(Integer id) {

        return FestivalUpdateRequestDto.builder().build();
    }


    //글 작성
    public Integer save(FestivalSaveRequestDto requestDto) {
        return fesposRepository.save(requestDto.toEntity()).getFestivalID();
    }


    //리스트에서 게시글 클릭해서 보는거. 게시글의 id를 받아와서 반환
    public static Festival viewDetail(Integer id){

        return fesposRepository.findById(id).get();
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
