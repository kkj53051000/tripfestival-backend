package com.tripfestival.service;

import com.tripfestival.repository.NatureHotspotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class NatureHotspotService {
    private final NatureHotspotRepository natureHotspotRepository;
}
