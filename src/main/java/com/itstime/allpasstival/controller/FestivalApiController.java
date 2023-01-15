package com.itstime.allpasstival.controller;

import com.itstime.allpasstival.domain.dto.Response;
import com.itstime.allpasstival.domain.dto.festival.*;
import com.itstime.allpasstival.domain.dto.post.PostDeleteResponse;
import com.itstime.allpasstival.domain.dto.post.PostLikeUpdateResponse;
import com.itstime.allpasstival.domain.dto.post.PostModifyRequest;
import com.itstime.allpasstival.domain.dto.post.PostModifyResponse;
import com.itstime.allpasstival.service.FestivalService;
import com.itstime.allpasstival.service.LikedPostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/festivals")
@RequiredArgsConstructor
@Slf4j
public class FestivalApiController {

    private final FestivalService festivalService ;
    private final LikedPostService likedPostService;

    //글 작성처리
    @PostMapping("")
    public Response<FestivalSaveResponseDto> saveFestival(@RequestBody FestivalSaveRequestDto requestDto){
        FestivalSaveResponseDto saveResponse = festivalService.save(requestDto);
        return Response.success(saveResponse);
    }

    //게시글 수정기능
    @PutMapping("/{id}")
    public Response<FestivalUpdateResponseDto> modifyfestival(@PathVariable Integer id, @RequestBody  FestivalUpdateRequestDto updateRequest){
        FestivalUpdateResponseDto festivalModifyResponse = festivalService.modifyfestival(id, updateRequest);
        return Response.success(festivalModifyResponse);

    }

    //축제글 세부 조회.
    @GetMapping(value="/{id}")
    public Response<FestivalDetailResponse> viewDetails(@PathVariable Integer id, Authentication authentication){
        FestivalDetailResponse festivalDetailResponse = festivalService.viewDetail(id);
        festivalService.updateRecentlyViewedFestival(id, authentication);
        return Response.success(festivalDetailResponse);
    }

    /* //삭제기능
     @GetMapping("/api/festivals/delete")
     public String Delete(Integer id){
         festivalService.Delete(id);
         //삭제 후 리스트로 다시 돌아감
         return "redirect:i/festivals/list";
     }*/
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
    @GetMapping("/list")
    public Response<Page<FestivalDetailResponse>> festivallist(@PageableDefault(size = 20, sort ="createdAt",
            direction = Sort.Direction.DESC)Pageable pageable){
        Page<FestivalDetailResponse> list = festivalService.festivalList(pageable);
        return Response.success(list);
    }

    //좋아요
    @GetMapping("/{id}/likes")
    public Response<Long> CntLike(@PathVariable Integer id){
        return Response.success(likedPostService.countLike(id));
    }

    //월별 일정관리



}
