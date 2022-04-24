package com.tripfestival.repository.event;

import com.tripfestival.domain.event.EventImg;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventImgRepository extends JpaRepository<EventImg, Long> {
}
