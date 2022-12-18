package com.itstime.allpasstival.repository;

import com.itstime.allpasstival.domain.entity.User;
import com.itstime.allpasstival.domain.entity.festival;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface festivalRepository extends JpaRepository<festival,Integer>{

    static Optional<festival> update(String festival_name, String holding_venue, String tel_num, String host_inst, String host_org, String finish_date, String homep_addr, String start_date, String street_addr, int view, String etc) {
        return null;
    }

    static Optional<festival> findById(int festivalID) {
        return null;
    }


    Page<festival> findByTitle(String Search);

}