package com.tripfestival.dto.world;

import com.tripfestival.request.world.WorldCountryCityRegionProcessRequest;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class WorldCountryCityRegionProcessDto {
    private MultipartFile file;
    private String name;
    private Long worldCountryCityId;

    public WorldCountryCityRegionProcessDto(MultipartFile file, WorldCountryCityRegionProcessRequest req) {
        this.file = file;
        this.name = req.getName();
        this.worldCountryCityId = req.getWorldCountryCityId();
    }
}
