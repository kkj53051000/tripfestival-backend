package com.tripfestival.request.hotsight;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HotSightTwoProcessRequest {
    private String name;
    private Long hotSightOneId;
}
