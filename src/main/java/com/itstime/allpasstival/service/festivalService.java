package com.itstime.allpasstival.service;


import com.itstime.allpasstival.domain.dto.festivalSaveRequestDto;
import com.itstime.allpasstival.domain.dto.festivalUpdateRequestDto;
import com.itstime.allpasstival.domain.entity.festival;
import com.itstime.allpasstival.repository.festivalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class festivalService {

    @Autowired
    private static final festivalRepository fesposRepository = null;




    //리스트 조회
    public static Page<festival> fesList(Pageable pageable){

        return fesposRepository.findAll(pageable);
    }

    public static festivalUpdateRequestDto findByid(Integer id) {
        return festivalUpdateRequestDto.builder().build();
    }


    //글 작성
    public String write(festival festivals) {
        fesposRepository.save(festivals);
        return null;
    }


    //리스트에서 게시글 클릭해서 보는거. 게시글의 id를 받아와서 반환
    public static festival viewDetail(Integer id){

        return fesposRepository.findById(id).get();
    }

    //검색기능
    public Page<festival> festivalSearch(String keyWord, Pageable pageable){
        return fesposRepository.findByKeyWordContaining(keyWord,pageable);
    }


    //게시글 삭제하는거
    //게시글 아이디 받아서 삭제
    public void Delete(Integer id){

        fesposRepository.deleteById((id));
    }
}
