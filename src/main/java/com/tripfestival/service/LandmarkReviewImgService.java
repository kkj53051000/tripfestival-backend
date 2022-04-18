package com.tripfestival.service;

import com.tripfestival.repository.LandmarkReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LandmarkReviewImgService {
    private final LandmarkReviewRepository landmarkReviewRepository;
}
