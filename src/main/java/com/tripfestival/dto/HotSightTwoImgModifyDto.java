package com.tripfestival.dto;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class HotSightTwoImgModifyDto {
    private Long hotSightTwoId;
    private MultipartFile file;

    public HotSightTwoImgModifyDto(Long hotSightTwoId, MultipartFile file) {
        this.hotSightTwoId = hotSightTwoId;
        this.file = file;
    }
}
