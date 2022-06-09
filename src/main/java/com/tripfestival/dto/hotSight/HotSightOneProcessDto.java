package com.tripfestival.dto.hotSight;

import com.tripfestival.request.hotsight.HotSightOneProcessRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotSightOneProcessDto {
    private MultipartFile file;
    private String name;

    public HotSightOneProcessDto(MultipartFile file, HotSightOneProcessRequest req) {
        this.file = file;
        this.name = req.getName();
    }
}
