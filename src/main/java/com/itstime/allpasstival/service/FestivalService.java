package com.itstime.allpasstival.service;


import com.itstime.allpasstival.domain.dto.festival.FestivalDetailResponse;
import com.itstime.allpasstival.domain.dto.festival.FestivalReserveResponse;
import com.itstime.allpasstival.domain.dto.festival.FestivalSaveRequestDto;
import com.itstime.allpasstival.domain.dto.festival.FestivalUpdateRequestDto;
import com.itstime.allpasstival.domain.entity.Festival;
import com.itstime.allpasstival.domain.entity.RecentlyViewedFestival;
import com.itstime.allpasstival.domain.entity.ReservedFestival;
import com.itstime.allpasstival.domain.entity.User;
import com.itstime.allpasstival.repository.FestivalRepository;
import com.itstime.allpasstival.repository.RecentlyViewedFestivalRepository;
import com.itstime.allpasstival.repository.ReservedFestivalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FestivalService {

    private final FestivalRepository festivalRepository;
    private final ValidateService validateService;
    private final ReservedFestivalRepository reservedFestivalRepository;

    private final RecentlyViewedFestivalRepository recentlyViewedFestivalRepository;




    //리스트 조회
    public static Page<Festival> fesList(Pageable pageable){

        return null;
    }

    public static FestivalUpdateRequestDto findByid(Integer id) {

        return FestivalUpdateRequestDto.builder().build();
    }


    //글 작성
    public Integer save(FestivalSaveRequestDto requestDto) {
        return festivalRepository.save(requestDto.toEntity()).getFestivalId();
    }


    //리스트에서 게시글 세부조회. 게시글의 id를 받아와서 반환
    public FestivalDetailResponse viewDetail(Integer id){
        Festival festival = festivalRepository.findById(id).get();
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
    public void Delete(Integer id){

        festivalRepository.deleteById((id));
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
}
