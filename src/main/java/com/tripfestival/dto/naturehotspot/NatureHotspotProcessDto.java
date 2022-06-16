package com.tripfestival.dto.naturehotspot;

import com.tripfestival.request.naturehotspot.NatureHotspotProcessRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
