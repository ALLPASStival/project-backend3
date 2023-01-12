package com.itstime.allpasstival.repository;

import com.itstime.allpasstival.domain.entity.Festival;
import com.itstime.allpasstival.domain.entity.ReservedFestival;
import com.itstime.allpasstival.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservedFestivalRepository extends JpaRepository<ReservedFestival,Integer> {
    Optional<ReservedFestival> findByFestivalAndUser(Festival festival, User user);
}
