package com.tripfestival.dto.naturehotspot;

import com.tripfestival.request.naturehotspot.NatureHotspotProcessRequest;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class NatureHotspotProcessDto {
    private MultipartFile file;
    private Long landmarkId;
    private Long natureHotspotTypeId;

    public NatureHotspotProcessDto(MultipartFile file, NatureHotspotProcessRequest req) {
        this.file = file;
        this.landmarkId = req.getLandmarkId();
        this.natureHotspotTypeId = req.getNatureHotspotTypeId();
    }
}
