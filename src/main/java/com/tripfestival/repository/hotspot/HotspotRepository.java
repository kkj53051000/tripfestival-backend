package com.tripfestival.repository.hotspot;

import com.tripfestival.domain.hotspot.Hotspot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotspotRepository extends JpaRepository<Hotspot, Long> {
}
