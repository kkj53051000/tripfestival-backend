package com.tripfestival.repository;

import com.tripfestival.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUid(String uId);
}
