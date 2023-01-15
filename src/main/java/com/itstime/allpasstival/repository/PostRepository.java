package com.itstime.allpasstival.repository;

import com.itstime.allpasstival.domain.entity.Post;
import com.itstime.allpasstival.domain.entity.User;
import com.itstime.allpasstival.enums.PostCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByUser(User user);
    Page<Post> findAllByUser(Pageable pageable, User user);
    Page<Post> findAllByCategory(Pageable pageable, PostCategory category);

}
