package com.itstime.allpasstival.repository;

import com.itstime.allpasstival.domain.entity.Festival;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FestivalRepository extends JpaRepository<Festival,Integer>{
    Page<Festival> findAllByFestivalNameContaining(String keyword, Pageable pageable);
    Page<Festival> findAllByStartDateStartsWith(String YearAndMonth, Pageable pageable);
    Page<Festival> findAllByContentContaining(String keyword, Pageable pageable);
    Page<Festival> findAllByStreetAddrStartingWithAndContentContainingAndStartDateContaining(String region, String category, String month, Pageable pageable);
    Page<Festival> findAllByLikesGreaterThan(Long zero,Pageable pageable);
    Page<Festival> findAllByReviewGreaterThan(Long zero,Pageable pageable);
    Optional<Festival> findByFestivalName(String festivalName);
}