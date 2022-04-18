package com.tripfestival.service;

import com.tripfestival.repository.LandmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LandmarkService {
    private final LandmarkRepository landmarkRepository;
}
