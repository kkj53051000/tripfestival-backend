package com.tripfestival.repository.naturehotspot;

import com.tripfestival.domain.naturehotspot.NatureHotspot;
import com.tripfestival.domain.naturehotspot.NatureHotspotType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NatureHotspotRepository extends JpaRepository<NatureHotspot, Long> {
    Optional<List<NatureHotspot>> findByNatureHotspotType(NatureHotspotType natureHotspotType);
}
