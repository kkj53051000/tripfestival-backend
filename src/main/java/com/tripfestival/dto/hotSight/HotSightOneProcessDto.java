package com.tripfestival.dto.hotSight;

import com.tripfestival.request.hotsight.HotSightOneProcessRequest;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class HotSightOneProcessDto {
    private MultipartFile file;
    private String name;

    public HotSightOneProcessDto(MultipartFile file, HotSightOneProcessRequest req) {
        this.file = file;
        this.name = req.getName();
    }
}
