package com.tripfestival.vo.user;

import com.tripfestival.vo.Response;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserKakaoLoginResponseVo {
    private Response status;
    private String jwtKey;

    public UserKakaoLoginResponseVo(Response status, String jwtKey) {
        this.status = status;
        this.jwtKey = jwtKey;
    }
}
