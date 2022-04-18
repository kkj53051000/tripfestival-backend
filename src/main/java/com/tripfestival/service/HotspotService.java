package com.tripfestival.service;

import com.tripfestival.repository.HotspotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class HotspotService {
    private final HotspotRepository hotspotRepository;
}
