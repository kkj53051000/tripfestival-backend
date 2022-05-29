package com.tripfestival.request.hotspot;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotspotProcessRequest {
    private Long landmarkId;
    private Long hotspotTypeId;
}
