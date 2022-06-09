package com.tripfestival.dto.hotSight;

import com.tripfestival.request.hotsight.HotSightOneNameModifyRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotSightOneNameModifyDto {
    private Long hotSightOneId;
    private String name;

    public HotSightOneNameModifyDto(Long hotSightOneId, HotSightOneNameModifyRequest req) {
        this.hotSightOneId = hotSightOneId;
        this.name = req.getName();
    }
}
