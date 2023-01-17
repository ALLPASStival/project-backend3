package com.itstime.allpasstival.domain.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.itstime.allpasstival.domain.dto.festival.FestivalLikedResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.List;


@Getter//클래스 내의 모든 getter 메소드 자동 생성
@NoArgsConstructor//기본생성자 자동 추가 Public Posts(){}같은거
@Entity //테이블과 링크될 클래스
@Builder
@AllArgsConstructor
public class Festival extends BaseEntity{
    @Id//프라임키.축제 id,VARCHAR
    @GeneratedValue(strategy = GenerationType.IDENTITY)//프라임키 생성 규칙. 이걸 추가하면 자동생성된대요
    private Integer festivalId;
    private String festivalName;//축제이름 VARCHAR
    private String holdingVenue;//개최장소 VARCHAR
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String startDate;//시작일시 DATE
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String finishDate;//종료일시 DATE
    private String hostInst;//주최기관 VARCHAR
    private String hostOrg;//주관기관 VARCHAR
    private String telNum;//전화번호 VARCHAR
    private String homepAddr;//홈페이지 주소 VARCHAR
    private String streetAddr;//도로명 주소 VARCHAR
    private Integer view;//조회수
    private String etc;//비고TEXT
    private String author;
    private String letitude;
    private String longitude;


    public static Festival of(ReservedFestival reservedFestival) {

        return reservedFestival.getFestival();
    }
    public static Festival of(RecentlyViewedFestival recentlyViewedFestival) {
        return recentlyViewedFestival.getFestival();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "festival", cascade = CascadeType.REMOVE, orphanRemoval = true)
    List<ReservedFestival> reservedFestivals = new ArrayList<>();

    @OneToMany(mappedBy = "festival", cascade = CascadeType.REMOVE, orphanRemoval = true)
    List<RecentlyViewedFestival> recentlyViewedFestivals = new ArrayList<>();

    @OneToMany(mappedBy = "festival", cascade = CascadeType.REMOVE, orphanRemoval = true)
    List<FestivalLiked> likedFestivals  = new ArrayList<>();

}
