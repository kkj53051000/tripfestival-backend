package com.tripfestival.request.naturehotspot;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NatureHotspotProcessRequest {
    private Long landmarkId;
    private Long natureHotspotTypeId;
}
