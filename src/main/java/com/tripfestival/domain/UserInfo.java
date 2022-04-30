package com.tripfestival.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserInfo {
    public Long userId;

    public UserInfo(Long userId) {
        this.userId = userId;
    }
}
