package com.tripfestival.vo;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseVo {
    private Response status;
    private String cause;
}
