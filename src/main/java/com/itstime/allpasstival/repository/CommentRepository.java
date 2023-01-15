package com.itstime.allpasstival.repository;

import com.itstime.allpasstival.domain.entity.Comment;
import com.itstime.allpasstival.domain.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    Page<Comment> findAllByPost(Post post, Pageable pageable);
}
