package com.tripfestival.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MainSearchResultVo {
    String title;
    String img;
    Long cityId;
    Long regionId;
}
