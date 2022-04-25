package com.tripfestival.dto.naturehotspot;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class NatureHotspotImgModifyDto {
    private Long natureHotspotId;
    private MultipartFile file;

    public NatureHotspotImgModifyDto(Long natureHotspotId, MultipartFile file) {
        this.natureHotspotId = natureHotspotId;
        this.file = file;
    }
}
