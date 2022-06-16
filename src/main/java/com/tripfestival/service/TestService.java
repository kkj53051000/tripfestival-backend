package com.tripfestival.service;

import com.tripfestival.domain.user.User;
import com.tripfestival.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Service
@RequiredArgsConstructor
public class TestService {

    private final UserRepository userRepository;

    @PostConstruct
    public void setup() {
        User user = User.builder()
                .uid("test")
                .build();
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public void read() {
        userRepository.findByUid("test");
    }
    @Transactional
    public void write() {
        userRepository.findByUid("test");
    }
}
