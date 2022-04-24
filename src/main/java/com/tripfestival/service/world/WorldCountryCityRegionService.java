package com.tripfestival.service.world;

import com.tripfestival.repository.world.WorldCountryCityRegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class WorldCountryCityRegionService {
    private final WorldCountryCityRegionRepository worldCountryCityRegionRepository;
}
