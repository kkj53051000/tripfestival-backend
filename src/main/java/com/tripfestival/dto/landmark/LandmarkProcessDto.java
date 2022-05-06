package com.tripfestival.dto.landmark;

import com.tripfestival.request.landmark.LandmarkProcessRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LandmarkProcessDto {
    private MultipartFile file;
    private String name;
    private String description;
    private String address;
    private String homepage;
    private Long worldCountryCityRegionId;

    public LandmarkProcessDto(MultipartFile file, LandmarkProcessRequest req) {
        this.file = file;
        this.name = req.getName();
        this.description = req.getDescription();
        this.address = req.getAddress();
        this.homepage = req.getHomepage();
        this.worldCountryCityRegionId = req.getWorldCountryCityRegionId();
    }
}
