package com.itstime.allpasstival.controller;

import com.itstime.allpasstival.domain.dto.Response;
import com.itstime.allpasstival.domain.dto.festival.*;
import com.itstime.allpasstival.domain.dto.post.PostInfoResponse;
import com.itstime.allpasstival.service.FestivalService;
import com.itstime.allpasstival.service.LikedPostService;
import com.itstime.allpasstival.service.ValidateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/festivals")
@RequiredArgsConstructor
@Slf4j
public class FestivalApiController {

    private final FestivalService festivalService ;
    private final LikedPostService likedPostService;
    private final ValidateService validateService;

    //축제 초기 데이터 넣기
    @PostMapping("/add")
    public String saveFestival() throws IOException {
        festivalService.addFestival();
        return "end";
    }

    //글 작성처리
    @PostMapping("")
    public Response<FestivalSaveResponseDto> saveFestival(@RequestBody FestivalSaveRequestDto requestDto){
        FestivalSaveResponseDto saveResponse = festivalService.festivalSave(requestDto);
        return Response.success(saveResponse);
    }

    //게시글 수정기능
    @PutMapping("/{id}")
    public Response<FestivalUpdateResponseDto> modifyFestival(@PathVariable Integer id, @RequestBody FestivalUpdateRequestDto updateRequest){
        FestivalUpdateResponseDto festivalModifyResponse = festivalService.modifyFestival(id, updateRequest);
        return Response.success(festivalModifyResponse);

    }


    //축제 검색
    @GetMapping("/search")
    public Response<Page<FestivalDetailResponse>> searchFestivals(@PageableDefault(size = 10, sort ="likes",
            direction = Sort.Direction.DESC) Pageable pageable, @RequestParam String keyword, @RequestParam String searchCategory) {
        Page<FestivalDetailResponse> festivalDetailResponses = festivalService.searchFestival(pageable, keyword, searchCategory);
        return Response.success(festivalDetailResponses);
    }

    //축제글 세부 조회.
    @GetMapping(value="/{id}")
    public Response<FestivalDetailResponse> viewDetails(@PathVariable Integer id, Authentication authentication){
        FestivalDetailResponse festivalDetailResponse = festivalService.viewDetail(id);
        festivalService.updateRecentlyViewedFestival(id, authentication);
        return Response.success(festivalDetailResponse);
    }

    //삭제기능
    @DeleteMapping("/{id}")
    public Response<FestivalDeleteResponse> deletePost(@PathVariable Integer id){
        FestivalDeleteResponse festivalDeleteResponse = festivalService.deleteFestival(id);
        return Response.success(festivalDeleteResponse);

    }

    //찜한 축제 수정
    @PostMapping("/{id}/reserves")
    public Response<FestivalReserveResponse> reserveFestival(@PathVariable Integer id, Authentication authentication){
        FestivalReserveResponse festivalReserveResponse = festivalService.updateReservedFestival(id, authentication.getName());
        return Response.success(festivalReserveResponse);
    }

    //리스트
    @GetMapping("")
    public Response<Page<FestivalDetailResponse>> festivalList(@PageableDefault(size = 10, sort ="startDate",
            direction = Sort.Direction.DESC)Pageable pageable){
        Page<FestivalDetailResponse> list = festivalService.festivalList(pageable);
        return Response.success(list);
    }

    //좋아요
    @GetMapping("/{id}/likes")
    public Response<Long> CntLike(@PathVariable Integer id){
        return Response.success(festivalService.cntLike(id));
    }

    //좋아요 등록&취소
    @PostMapping("/{id}/likes")
    public Response<FestivalLikedResponse> AddLike(@PathVariable Integer id, Authentication authentication){
        FestivalLikedResponse likeUpdateResponse = festivalService.updateLike(id, authentication.getName());
        return Response.success(likeUpdateResponse);
    }

    //지도에 표시하기
    @GetMapping("/maps")
    public Response<Page<FestivalMapResponse>> festivalMapList(@PageableDefault(size = 60, sort ="startDate",
            direction = Sort.Direction.DESC)Pageable pageable){
        Page<FestivalMapResponse> list = festivalService.festivalMapList(pageable);
        return Response.success(list);
    }

    // 축제 좋아요 순위
    @GetMapping("/ranks/likes")
    public Response<Page<FestivalDetailResponse>> festivalRankByLike(@PageableDefault(size = 4, sort ="likes",
            direction = Sort.Direction.DESC)Pageable pageable){
        Page<FestivalDetailResponse> list = festivalService.festivalRankByLikes(pageable);
        return Response.success(list);
    }

    // 축제 리뷰 순위
    @GetMapping("/ranks/reviews")
    public Response<Page<FestivalDetailResponse>> festivalRankByReview(@PageableDefault(size = 4, sort ="review",
            direction = Sort.Direction.DESC)Pageable pageable){
        Page<FestivalDetailResponse> list = festivalService.festivalRankByReview(pageable);
        return Response.success(list);
    }

}
