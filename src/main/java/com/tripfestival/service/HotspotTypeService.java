package com.tripfestival.service;

import com.tripfestival.repository.HotspotTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class HotspotTypeService {
    private final HotspotTypeRepository hotspotTypeRepository;
}
