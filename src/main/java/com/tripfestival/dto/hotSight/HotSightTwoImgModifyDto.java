package com.tripfestival.dto.hotSight;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
@NoArgsConstructor
public class HotSightTwoImgModifyDto {
    private Long hotSightTwoId;
    private MultipartFile file;

    public HotSightTwoImgModifyDto(Long hotSightTwoId, MultipartFile file) {
        this.hotSightTwoId = hotSightTwoId;
        this.file = file;
    }
}
