package com.itstime.allpasstival.repository;

import com.itstime.allpasstival.domain.entity.Festival;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FestivalRepository extends JpaRepository<Festival,Integer>{


    //검색기능 시작
    /*Page<Festival> findByKeyWordContaining(String Search, Pageable pageable);
*/
}