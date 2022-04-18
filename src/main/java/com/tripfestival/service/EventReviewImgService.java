package com.tripfestival.service;

import com.tripfestival.repository.EventReviewImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EventReviewImgService {
    private final EventReviewImgRepository eventReviewImgRepository;
}
