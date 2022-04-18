package com.tripfestival.service;

import com.tripfestival.repository.EventSeasonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EventSeasonService {
    private final EventSeasonRepository eventSeasonRepository;
}
