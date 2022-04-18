package com.tripfestival.vo;

import lombok.Builder;

@Builder
public class ResponseVo {
    private Response status;
    private String cause;
}
