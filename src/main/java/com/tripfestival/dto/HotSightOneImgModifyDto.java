package com.tripfestival.dto;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class HotSightOneImgModifyDto {
    private Long hotSightOneId;
    private MultipartFile file;

    public HotSightOneImgModifyDto(Long hotSightOneId, MultipartFile file) {
        this.hotSightOneId = hotSightOneId;
        this.file = file;
    }
}
