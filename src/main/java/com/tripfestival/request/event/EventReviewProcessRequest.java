package com.tripfestival.request.event;

import lombok.Getter;

@Getter
public class EventReviewProcessRequest {
    private String content;
    private byte score;
}
