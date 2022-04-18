package com.tripfestival.repository;

import com.tripfestival.domain.Hotspot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotspotRepository extends JpaRepository<Hotspot, Long> {
}
