package com.itstime.allpasstival.repository;

import com.itstime.allpasstival.domain.entity.LikedPost;
import com.itstime.allpasstival.domain.entity.Post;
import com.itstime.allpasstival.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LikedPostRepository extends JpaRepository<LikedPost, Integer> {
    Optional<LikedPost> findByPostAndUser(Post post, User user);
    @Query(value = "SELECT COUNT(*) FROM liked_post likes where likes.post_id = :post_id", nativeQuery = true)
    Long countAllByPostId(@Param("post_id") Integer postId);
}
