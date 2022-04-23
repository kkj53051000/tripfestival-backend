package com.tripfestival.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class KakaoLoginDto {
    private Long id;

    public KakaoLoginDto(Long id) {
        this.id = id;
    }
}
