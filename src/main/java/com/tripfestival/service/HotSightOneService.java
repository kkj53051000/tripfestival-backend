package com.tripfestival.service;

import com.tripfestival.repository.HotSightOneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class HotSightOneService {
    private final HotSightOneRepository hotSightOneRepository;
}