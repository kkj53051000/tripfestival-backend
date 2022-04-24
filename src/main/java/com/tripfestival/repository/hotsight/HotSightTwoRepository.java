package com.tripfestival.repository.hotsight;

import com.tripfestival.domain.hotsight.HotSightOne;
import com.tripfestival.domain.hotsight.HotSightTwo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HotSightTwoRepository extends JpaRepository<HotSightTwo, Long> {
    Optional<List<HotSightTwo>> findByHotSightOne(HotSightOne hotSightOne);
}
