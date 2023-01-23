package com.itstime.allpasstival.service;


import com.itstime.allpasstival.api.NaverSearchController;
import com.itstime.allpasstival.domain.dto.festival.*;
import com.itstime.allpasstival.domain.entity.*;
import com.itstime.allpasstival.repository.LikedFestivalRepository;
import com.itstime.allpasstival.repository.FestivalRepository;
import com.itstime.allpasstival.repository.RecentlyViewedFestivalRepository;
import com.itstime.allpasstival.repository.ReservedFestivalRepository;
import com.itstime.allpasstival.utils.FestivalCSVParsing;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FestivalService {

    private final FestivalRepository festivalRepository;
    private final ValidateService validateService;
    private final ReservedFestivalRepository reservedFestivalRepository;

    private final RecentlyViewedFestivalRepository recentlyViewedFestivalRepository;

    private final LikedFestivalRepository likedFestivalRepository;

    //축제 초기 데이터 넣기
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
            String url = null;
            //구글 검색 api
//            Map imageSearch =GoogleImageSearch.imageSearch(line[0]);
//            ArrayList list = (ArrayList) imageSearch.get("items");
//            LinkedHashMap linkedHashMap = (LinkedHashMap) list.get(0);
//            url = (String) linkedHashMap.get("link");
            //네이버 검색 api
            url = NaverSearchController.getImage(line[0]);
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
                    .review(Long.parseLong("0"))
                    .likes(Long.parseLong("0"))
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

    //축제 랭킹 조회(좋아요 순)
    public Page<FestivalDetailResponse> festivalRankByLikes(Pageable pageable){
        Page<Festival> festivalPage = festivalRepository.findAllByLikesGreaterThan(Long.parseLong("0"),pageable);
        return festivalPage.map(FestivalDetailResponse::of);
    }

    //축제 랭킹 조회(리뷰 순)
    public Page<FestivalDetailResponse> festivalRankByReview(Pageable pageable){
        Page<Festival> festivalPage = festivalRepository.findAllByReviewGreaterThan(Long.parseLong("0"),pageable);
        return festivalPage.map(FestivalDetailResponse::of);
    }

    //축제 작성
    public FestivalSaveResponseDto festivalSave(FestivalSaveRequestDto requestDto) {
        Festival festival = festivalRepository.save(requestDto.toEntity());
        return FestivalSaveResponseDto.of(festival);
    }

    //축제 수정
    public FestivalUpdateResponseDto modifyFestival(Integer id, FestivalUpdateRequestDto updateRequest){
        Festival festival = validateService.validateFestival(id);
        Festival modifiedFestival = festivalRepository.save(updateRequest.toEntity(festival));
        return FestivalUpdateResponseDto.of(modifiedFestival);
    }

    //축제 단건 조회
    public FestivalDetailResponse viewDetail(Integer id){
        Festival festival = validateService.validateFestival(id);
        return FestivalDetailResponse.of(festival);
    }

    //검색기능
    public Page<FestivalDetailResponse> festivalSearch(String keyWord, Pageable pageable){
     Page<Festival> festival = festivalRepository.findAllByFestivalNameContaining(keyWord,pageable);
     return festival.map(FestivalDetailResponse::of);
    }


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


    //좋아요 누르기/취소
    public FestivalLikedResponse updateLike(Integer id,String userId){
        User user = validateService.validateUser(userId);
        Festival festival = validateService.validateFestival(id);
        Optional<LikedFestival> likedFestival = likedFestivalRepository.findByFestivalAndUser(festival,user);
        if(likedFestival.isPresent()){
            validateService.validatePermission(likedFestival.get().getUser(),user);
            likedFestivalRepository.delete(likedFestival.get());
            festival.changeLike(festival.getLikes()-1);
            festivalRepository.save(festival);
            System.out.println("dd");
            return FestivalLikedResponse.builder()
                    .message("좋아요를 삭제했습니다.")
                    .build();
        }
        else{
            likedFestivalRepository.save(LikedFestival.of(festival,user));
            festival.changeLike(festival.getLikes()+1);
            festivalRepository.save(festival);
            return FestivalLikedResponse.builder()
                    .message("좋아요를 눌렀습니다.")
                    .build();
        }
    }

    public Long cntLike(Integer id){
        Festival festival = validateService.validateFestival(id);
        return likedFestivalRepository.countAllByFestivalId(id);
    }

    public Page<FestivalMapResponse> festivalMapList(Pageable pageable) {
        Page<Festival> festivalPage = festivalRepository.findAll(pageable);
        return festivalPage.map(FestivalMapResponse::of);
    }
}
