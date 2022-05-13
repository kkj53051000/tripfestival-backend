package com.tripfestival.repository.event;

import com.tripfestival.domain.event.Event;
import com.tripfestival.domain.event.EventFee;
import com.tripfestival.domain.world.WorldCountryCity;
import com.tripfestival.domain.world.WorldCountryCityRegion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByWorldCountryCityRegion(WorldCountryCityRegion worldCountryCityRegion);

    List<Event> findByWorldCountryCityRegion_WorldCountryCity(WorldCountryCity worldCountryCity);
}
