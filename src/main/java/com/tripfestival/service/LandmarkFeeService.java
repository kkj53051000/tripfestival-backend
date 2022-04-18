package com.tripfestival.service;

import com.tripfestival.repository.LandmarkFeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LandmarkFeeService {
    private final LandmarkFeeRepository landmarkFeeRepository;
}
