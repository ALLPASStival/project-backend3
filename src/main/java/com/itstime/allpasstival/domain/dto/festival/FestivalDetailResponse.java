package com.itstime.allpasstival.domain.dto.festival;
//게시글 응답 클래스

import com.itstime.allpasstival.domain.entity.Festival;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FestivalDetailResponse {
    private String festivalName;//축제이름VARCHAR
    private String holdingVenue;//개최장소VARCHAR
    private String startDate;//시작일시DATE
    private String finishDate;//종료일시DATE
    private String hostInst;//주관기관VARCHAR
    private String hostOrg;//주최기관VARCHAR
    private String telNum;//전화번호VARCHAR
    private String homepAddr;//홈페이지 주소VARCHAR
    private String streetAddr;//도로명 주소VARCHAR
    private Integer view;//조회수
    private String etc;//비고TEXT
    private String author;//작성자
    private Long likes;
    private Integer review;

    public static FestivalDetailResponse of(Festival festival) {
        return FestivalDetailResponse.builder().
                holdingVenue(festival.getHoldingVenue()).
                hostInst(festival.getHostInst()).
                telNum(festival.getTelNum()).
                festivalName(festival.getFestivalName()).
                hostOrg(festival.getHostOrg()).
                etc(festival.getEtc()).
                view(festival.getView()).
                finishDate(festival.getFinishDate()).
                likes(festival.getLikes()).
                startDate(festival.getStartDate()).
                homepAddr(festival.getHomepAddr()).
                streetAddr(festival.getStreetAddr()).
                review(festival.getReviews().size()).
                author(festival.getAuthor()).
                build();
    }
}
