package com.tripfestival.request.landmark;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LandmarkFeeModifyRequest {
    private String title;
    private int price;
}
