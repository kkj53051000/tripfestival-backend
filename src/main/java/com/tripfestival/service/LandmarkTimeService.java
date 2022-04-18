package com.tripfestival.service;

import com.tripfestival.repository.LandmarkTimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LandmarkTimeService {
    private final LandmarkTimeRepository landmarkTimeRepository;
}
