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
    Page<Festival> findAllByFestivalNameContaining(String keyWord, Pageable pageable);
/*

    static Optional<Festival>  findByid(int festivalID) {
//        return null;
//    }
//
    //검색기능 시작
    //Page<Festival> findByKeyWordContaining(String Search, Pageable pageable);
*/

}