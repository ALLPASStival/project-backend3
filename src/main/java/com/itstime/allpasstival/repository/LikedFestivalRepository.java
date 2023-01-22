package com.itstime.allpasstival.repository;

import com.itstime.allpasstival.domain.entity.Festival;
import com.itstime.allpasstival.domain.entity.LikedFestival;
import com.itstime.allpasstival.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LikedFestivalRepository extends JpaRepository<LikedFestival, Integer> {
    Optional<LikedFestival> findByFestivalAndUser(Festival festival, User user);
    @Query(value = "SELECT COUNT(*) FROM liked_festival likes where likes.festival_id = :festival_id", nativeQuery = true)
    Long countAllByFestivalId(@Param("festival_id") Integer festivalId);

}
