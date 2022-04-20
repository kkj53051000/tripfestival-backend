package com.tripfestival.service;

import com.tripfestival.repository.HotSightLandmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class HotSightLandmarkService {
    private final HotSightLandmarkRepository hotSightLandmarkRepository;
}