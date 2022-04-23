package com.tripfestival.service;

import com.tripfestival.repository.NatureHotspotTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class NatureHotspotTypeService {
    private final NatureHotspotTypeRepository natureHotspotTypeRepository;
}
