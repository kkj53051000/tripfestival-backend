package com.tripfestival.service;

import com.tripfestival.repository.HotSightTwoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class HotSightTwoService {
    private final HotSightTwoRepository hotSightTwoRepository;
}
