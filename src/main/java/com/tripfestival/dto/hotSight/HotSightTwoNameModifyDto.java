package com.tripfestival.dto.hotSight;

import com.tripfestival.request.hotsight.HotSightTwoNameModifyRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotSightTwoNameModifyDto {
    private Long hotSightTwoId;
    private String name;

    public HotSightTwoNameModifyDto(Long hotSightTwoId, HotSightTwoNameModifyRequest req) {
        this.hotSightTwoId = hotSightTwoId;
        this.name = req.getName();
    }
}
