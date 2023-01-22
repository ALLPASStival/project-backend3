package com.itstime.allpasstival.domain.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private String startDate;//시작일시 DATE
    private String finishDate;//종료일시 DATE
    private String content; //축제 내용
    private String hostInst;//주최기관 VARCHAR
    private String hostOrg;//주관기관 VARCHAR
    private String telNum;//전화번호 VARCHAR
    private String homepAddr;//홈페이지 주소 VARCHAR
    private String streetAddr;//도로명 주소 VARCHAR
    private Integer view;//조회수
    @Column(columnDefinition = "TEXT")
    private String etc;//축제 관련 이미지 url
    private String author;
    private String latitude; //위도
    private String longitude; //경도
    private Long likes=Long.parseLong("0"); //좋아요수


    @OneToMany(mappedBy = "festival", cascade = CascadeType.REMOVE, orphanRemoval = true)
    List<ReservedFestival> reservedFestivals = new ArrayList<>();

    @OneToMany(mappedBy = "festival", cascade = CascadeType.REMOVE, orphanRemoval = true)
    List<RecentlyViewedFestival> recentlyViewedFestivals = new ArrayList<>();

    @OneToMany(mappedBy = "festival", cascade = CascadeType.REMOVE, orphanRemoval = true)
    List<LikedFestival> likedFestivals  = new ArrayList<>();

    @OneToMany(mappedBy = "festival", cascade = CascadeType.REMOVE, orphanRemoval = true)
    List<Post> reviews  = new ArrayList<>();

    public void changeLike(Long likes){
        this.likes=likes;
    }

    public static Festival of(ReservedFestival reservedFestival) {

        return reservedFestival.getFestival();
    }
    public static Festival of(RecentlyViewedFestival recentlyViewedFestival) {
        return recentlyViewedFestival.getFestival();
    }

}
