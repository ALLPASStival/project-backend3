package com.itstime.allpasstival.service;


import com.itstime.allpasstival.domain.dto.festival.*;
import com.itstime.allpasstival.domain.dto.post.PostDeleteResponse;
import com.itstime.allpasstival.domain.entity.*;
import com.itstime.allpasstival.repository.FestivalLikedRepository;
import com.itstime.allpasstival.repository.FestivalRepository;
import com.itstime.allpasstival.repository.RecentlyViewedFestivalRepository;
import com.itstime.allpasstival.repository.ReservedFestivalRepository;
import com.itstime.allpasstival.utils.FestivalCSVParsing;
import com.itstime.allpasstival.utils.GoogleImageSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FestivalService {

    private final FestivalRepository festivalRepository;
    private final ValidateService validateService;
    private final ReservedFestivalRepository reservedFestivalRepository;

    private final RecentlyViewedFestivalRepository recentlyViewedFestivalRepository;

    private final FestivalLikedRepository festivalLikedRepository;

    public void addFestival() throws IOException {
        FestivalCSVParsing festivalCSVParsing = new FestivalCSVParsing("C:\\Users\\HeongJi\\Downloads\\test.txt");
        String[] line=null;
        while((line = festivalCSVParsing.nextRead())!=null){
            int cnt = 0;
            for(String a : line){
                System.out.print(cnt++);
                System.out.print(" ");
                System.out.println(a);
            }
            Map imageSearch =GoogleImageSearch.imageSearch(line[0]);
            ArrayList list = (ArrayList) imageSearch.get("items");
            LinkedHashMap linkedHashMap = (LinkedHashMap) list.get(0);
            String url = (String) linkedHashMap.get("link");
            System.out.println(url);
            Festival festival = Festival.builder()
                    .festivalName(line[0])
                    .holdingVenue(line[1])
                    .startDate(line[2])
                    .finishDate(line[3])
                    .content(line[4])
                    .hostOrg(line[5])
                    .hostInst(line[6])
                    .etc(url)
                    .telNum(line[7])
                    .homepAddr(line[9])
                    .streetAddr(line[11])
                    .latitude(line[13])
                    .longitude(line[14])
                    .build();
            festivalRepository.save(festival);
            System.out.println();
        }
    }


    //리스트 조회
    public Page<FestivalDetailResponse> festivalList(Pageable pageable){
        Page<Festival> festivalPage = festivalRepository.findAll(pageable);
        return festivalPage.map(FestivalDetailResponse::of);
    }
    public static FestivalUpdateRequestDto findByid(Integer id) {

        return FestivalUpdateRequestDto.builder().build();
    }


    //글 작성
    public FestivalSaveResponseDto save(FestivalSaveRequestDto requestDto) {
        Festival festival = festivalRepository.save(requestDto.toEntity());
        return FestivalSaveResponseDto.of(festival);
    }
    //게시글 수정
    public FestivalUpdateResponseDto modifyfestival(Integer id, FestivalUpdateRequestDto updateRequest){
        Festival festival = validateService.validateFestival(id);
        Festival modifiedFestival = festivalRepository.save(updateRequest.toEntity(festival));
        return FestivalUpdateResponseDto.of(modifiedFestival);
    }



    //리스트에서 게시글 세부조회. 게시글의 id를 받아와서 반환
    public FestivalDetailResponse viewDetail(Integer id){
        Festival festival = festivalRepository.findById(id).get();
        // 구글 검색 api 이용시 리턴되는 형식에 따라 이미지 링크 뽑아냄
        //System.out.println(linkedHashMap.get("link"));
        return FestivalDetailResponse.builder().
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
    /// return festivalRepository
    //.findByKeyWordContaining(keyWord,pageable);
    ///}


    //게시글 삭제하는거
    //게시글 아이디 받아서 삭제
    public FestivalDeleteResponse deleteFestival(Integer id){
        Festival festival = validateService.validateFestival(id);
        festivalRepository.deleteById(id);
        return FestivalDeleteResponse.of(festival);
    }


    //찜한 축제 수정
    public FestivalReserveResponse updateReservedFestival(Integer festivalId, String userId){
        User user = validateService.validateUser(userId);
        Festival festival = validateService.validateFestival(festivalId);
        Optional<ReservedFestival> reservedFestival = reservedFestivalRepository.findByFestivalAndUser(festival,user);
        if(reservedFestival.isPresent()){
            validateService.validatePermission(reservedFestival.get().getUser(),user);
            reservedFestivalRepository.delete(reservedFestival.get());
            return FestivalReserveResponse.of(festival, "찜을 삭제했습니다.");
        }
        reservedFestivalRepository.save(ReservedFestival.of(festival,user));
        return FestivalReserveResponse.of(festival,"찜을 추가했습니다.");
    }

    //찜한 축제 리스트 보기(페이징)
    public Page<FestivalDetailResponse> getReservedFestival(Pageable pageable, String userId) {
        User user = validateService.validateUser(userId);
        Page<ReservedFestival> reservedFestivalPage = reservedFestivalRepository.findAllByUser(pageable, user);
        Page<Festival> festivalPage = reservedFestivalPage.map(Festival::of);
        return festivalPage.map(FestivalDetailResponse::of);

    }

    //최근 조회한 페스티벌 정보 수정
    public void updateRecentlyViewedFestival(Integer festivalId, Authentication authentication) {
        if(authentication==null){
            return;
        }
        Festival festival = validateService.validateFestival(festivalId);
        String userId = authentication.getName();
        User user = validateService.validateUser(userId);
        if(recentlyViewedFestivalRepository.findAllByUser(user).size()>=12){
            recentlyViewedFestivalRepository.delete(recentlyViewedFestivalRepository.findByUserOrderByCreatedAtAsc(user));
        }
        if(recentlyViewedFestivalRepository.findByFestivalAndUser(festival,user).isPresent()){
            return;
        }
        recentlyViewedFestivalRepository.save(RecentlyViewedFestival.of(festival,user));
    }

    //최근 조회한 페스티벌 정보 리스트 보기(페이징)
    public Page<FestivalDetailResponse> getRecentlyViewedFestival(Pageable pageable, String userId) {
        User user = validateService.validateUser(userId);
        Page<RecentlyViewedFestival> recentlyViewedFestivalPage = recentlyViewedFestivalRepository.findAllByUser(pageable, user);
        Page<Festival> festivalPage = recentlyViewedFestivalPage.map(Festival::of);
        return festivalPage.map(FestivalDetailResponse::of);
    }


    public FestivalLikedResponse upDate(Integer id,String userId){
        User user = validateService.validateUser(userId);
        Festival festival = validateService.validateFestival(id);
        Optional<FestivalLiked> festivalLiked = festivalLikedRepository.findByFestivalAndUser(festival,user);
        if(festivalLiked.isPresent()){
            validateService.validatePermission(festivalLiked.get().getUser(),user);
            festivalLikedRepository.delete(festivalLiked.get());
            return FestivalLikedResponse.builder()
                    .message("좋아요를 삭제했습니다.")
                    .build();
        }
        else{
            festivalLikedRepository.save(FestivalLiked.of(festival,user));
            return FestivalLikedResponse.builder()
                    .message("좋아요를 눌렀습니다.")
                    .build();
        }
    }//서비스 계층 : 유저정보있는지 확인, 그리고 포스트 정보도 있는지 확인. 레포지토리 인터페이스에 JPA 상속받아서 생긴 기능으로
    //만약 눌려있다면-> delete()함수로 좋아요를 지우고,

    public Long cntLike(Integer id){;
        return festivalLikedRepository.countAllByFestivalId(id);
    }

}
