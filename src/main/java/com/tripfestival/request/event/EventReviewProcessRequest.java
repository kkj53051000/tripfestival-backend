package com.tripfestival.request.event;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EventReviewProcessRequest {
    private String content;
    private byte score;
}
