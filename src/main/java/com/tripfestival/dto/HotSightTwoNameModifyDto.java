package com.tripfestival.dto;

import com.tripfestival.request.HotSightTwoNameModifyRequest;
import lombok.Getter;

@Getter
public class HotSightTwoNameModifyDto {
    private Long hotSightTwoId;
    private String name;

    public HotSightTwoNameModifyDto(Long hotSightTwoId, HotSightTwoNameModifyRequest req) {
        this.hotSightTwoId = hotSightTwoId;
        this.name = req.getName();
    }
}
