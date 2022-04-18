package com.tripfestival.service;

import com.tripfestival.repository.WorldCountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class WorldCountryService {
    private final WorldCountryRepository worldCountryRepository;
}
