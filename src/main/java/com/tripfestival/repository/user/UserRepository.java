package com.tripfestival.repository.user;

import com.tripfestival.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUid(String uId);
}
