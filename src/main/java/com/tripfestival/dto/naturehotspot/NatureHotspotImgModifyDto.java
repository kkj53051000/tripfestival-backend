package com.tripfestival.dto.naturehotspot;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
@NoArgsConstructor
public class NatureHotspotImgModifyDto {
    private Long natureHotspotId;
    private MultipartFile file;

    public NatureHotspotImgModifyDto(Long natureHotspotId, MultipartFile file) {
        this.natureHotspotId = natureHotspotId;
        this.file = file;
    }
}
