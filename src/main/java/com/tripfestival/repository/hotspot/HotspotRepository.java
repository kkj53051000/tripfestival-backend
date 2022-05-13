package com.tripfestival.repository.hotspot;

import com.tripfestival.domain.hotspot.Hotspot;
import com.tripfestival.domain.hotspot.HotspotType;
import com.tripfestival.domain.world.WorldCountryCity;
import com.tripfestival.domain.world.WorldCountryCityRegion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotspotRepository extends JpaRepository<Hotspot, Long> {
    List<Hotspot> findByHotspotType(HotspotType hotspotType);

    List<Hotspot> findByHotspotTypeAndLandmark_WorldCountryCityRegion_WorldCountryCity(HotspotType hotspotType, WorldCountryCity worldCountryCity);

    List<Hotspot> findByHotspotTypeAndLandmark_WorldCountryCityRegion(HotspotType hotspotType, WorldCountryCityRegion worldCountryCityRegion);
}
