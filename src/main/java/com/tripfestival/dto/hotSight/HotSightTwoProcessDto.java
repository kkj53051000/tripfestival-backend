package com.tripfestival.dto.hotSight;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
public class HotSightTwoProcessDto {
    private MultipartFile file;
    private String name;
    private Long hotSightOneId;
}
