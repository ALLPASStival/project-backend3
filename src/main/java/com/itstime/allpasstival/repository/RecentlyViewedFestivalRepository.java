package com.itstime.allpasstival.repository;

import com.itstime.allpasstival.domain.entity.Festival;
import com.itstime.allpasstival.domain.entity.RecentlyViewedFestival;
import com.itstime.allpasstival.domain.entity.ReservedFestival;
import com.itstime.allpasstival.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecentlyViewedFestivalRepository extends JpaRepository<RecentlyViewedFestival,Integer> {
    Page<RecentlyViewedFestival> findAllByUser(Pageable pageable, User user);
    Optional<RecentlyViewedFestival> findByFestivalAndUser(Festival festival, User user);
    List<RecentlyViewedFestival> findAllByUser(User user);
    RecentlyViewedFestival findByUserOrderByCreatedAtAsc(User user);
}
