package com.tripfestival.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EventCategoryNameModifyDto {
    private Long eventCategoryId;
    private String name;
}
