package com.tripfestival.dto.user;

import lombok.Getter;

@Getter
public class KakaoLoginDto {
    private Long id;

    public KakaoLoginDto(Long id) {
        this.id = id;
    }
}
