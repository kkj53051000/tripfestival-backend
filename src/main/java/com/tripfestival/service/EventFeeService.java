package com.tripfestival.service;

import com.tripfestival.repository.EventFeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EventFeeService {
    private final EventFeeRepository eventFeeRepository;
}
