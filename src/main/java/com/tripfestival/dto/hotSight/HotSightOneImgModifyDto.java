package com.tripfestival.dto.hotSight;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
@NoArgsConstructor
public class HotSightOneImgModifyDto {
    private Long hotSightOneId;
    private MultipartFile file;

    public HotSightOneImgModifyDto(Long hotSightOneId, MultipartFile file) {
        this.hotSightOneId = hotSightOneId;
        this.file = file;
    }
}
