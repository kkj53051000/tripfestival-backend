package com.tripfestival.dto;

import com.tripfestival.request.HotSightOneProcessRequest;
import lombok.Builder;
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
