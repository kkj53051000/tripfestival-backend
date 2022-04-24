package com.tripfestival.repository.hotsight;

import com.tripfestival.domain.hotsight.HotSightLandmark;
import com.tripfestival.domain.hotsight.HotSightTwo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HotSightLandmarkRepository extends JpaRepository<HotSightLandmark, Long> {
    Optional<List<HotSightLandmark>> findByHotSightTwo(HotSightTwo hotSightTwo);
}
