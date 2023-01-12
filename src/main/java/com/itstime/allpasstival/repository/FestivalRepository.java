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
//    static Optional<Festival> update(String festival_name, String holding_venue, String tel_num, String host_inst, String host_org, String finish_date, String homep_addr, String start_date, String street_addr, int view, String etc) {
//        return null;
//    }
//
//    static Optional<Festival>  findByid(int festivalID) {
//        return null;
//    }
//
//    //검색기능 시작
//    Page<Festival> findByKeyWordContaining(String Search, Pageable pageable);

}