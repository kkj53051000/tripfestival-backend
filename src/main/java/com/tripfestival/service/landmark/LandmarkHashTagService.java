package com.tripfestival.service.landmark;

import com.tripfestival.repository.landmark.LandmarkHashTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LandmarkHashTagService {
    private final LandmarkHashTagRepository landmarkHashTagRepository;
}
