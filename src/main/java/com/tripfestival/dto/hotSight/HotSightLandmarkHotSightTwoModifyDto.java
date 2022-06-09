package com.tripfestival.dto.hotSight;

import com.tripfestival.request.hotsight.HotSightLandmarkHotSightTwoModifyRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotSightLandmarkHotSightTwoModifyDto {
    private Long hotSightLandmarkId;
    private Long hotSightTwoId;

    public HotSightLandmarkHotSightTwoModifyDto(Long hotSightLandmarkId, HotSightLandmarkHotSightTwoModifyRequest req) {
        this.hotSightLandmarkId = hotSightLandmarkId;
        this.hotSightTwoId = req.getHotSightTowId();
    }
}
