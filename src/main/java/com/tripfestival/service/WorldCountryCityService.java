package com.tripfestival.service;

import com.tripfestival.repository.WorldCountryCityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class WorldCountryCityService {
    private final WorldCountryCityRepository worldCountryCityRepository;
}
