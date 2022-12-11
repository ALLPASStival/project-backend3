package com.itstime.allpasstival.repository;

import com.itstime.allpasstival.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

}
