package com.tripfestival.service.event;

import com.tripfestival.repository.event.EventHashTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EventHashTagService {
    private final EventHashTagRepository eventHashTagRepository;
}
