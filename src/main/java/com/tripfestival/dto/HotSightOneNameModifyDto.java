package com.tripfestival.dto;

import com.tripfestival.request.HotSightOneNameModifyRequest;
import lombok.Getter;

@Getter
public class HotSightOneNameModifyDto {
    private Long hotSightOneId;
    private String name;

    public HotSightOneNameModifyDto(Long hotSightOneId, HotSightOneNameModifyRequest req) {
        this.hotSightOneId = hotSightOneId;
        this.name = req.getName();
    }
}
