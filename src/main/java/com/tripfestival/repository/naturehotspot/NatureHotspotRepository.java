package com.tripfestival.repository.naturehotspot;

import com.tripfestival.domain.naturehotspot.NatureHotspot;
import com.tripfestival.domain.naturehotspot.NatureHotspotType;
import com.tripfestival.domain.world.WorldCountryCity;
import com.tripfestival.domain.world.WorldCountryCityRegion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NatureHotspotRepository extends JpaRepository<NatureHotspot, Long> {
    List<NatureHotspot> findByNatureHotspotType(NatureHotspotType natureHotspotType);

    List<NatureHotspot> findByNatureHotspotTypeAndLandmark_WorldCountryCityRegion(NatureHotspotType natureHotspotType, WorldCountryCityRegion worldCountryCityRegion);

    List<NatureHotspot> findByNatureHotspotTypeAndLandmark_WorldCountryCityRegion_WorldCountryCity(NatureHotspotType natureHotspotType, WorldCountryCity worldCountryCity);

}
