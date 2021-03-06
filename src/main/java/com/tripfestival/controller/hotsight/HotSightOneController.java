package com.tripfestival.controller.hotsight;

import com.tripfestival.dto.hotSight.HotSightOneImgModifyDto;
import com.tripfestival.dto.hotSight.HotSightOneNameModifyDto;
import com.tripfestival.dto.hotSight.HotSightOneProcessDto;
import com.tripfestival.request.hotsight.HotSightOneNameModifyRequest;
import com.tripfestival.request.hotsight.HotSightOneProcessRequest;
import com.tripfestival.service.hotsight.HotSightOneService;
import com.tripfestival.vo.hotsight.HotSightOneListVo;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class HotSightOneController {  // 특별한 관광지 종류 1
    private final HotSightOneService hotSightOneService;

    @PostMapping("/admin/hotSightOneProcess")
    public ResponseVo hotSightOneProcess(
            @RequestPart(name ="file") MultipartFile file,
            @RequestPart(name ="value") HotSightOneProcessRequest req) {
        HotSightOneProcessDto hotSightOneProcessDto = new HotSightOneProcessDto(file, req);

        return hotSightOneService.hotSightOneInsert(hotSightOneProcessDto);
    }

    @PostMapping("/admin/hotSightOneRemove/{id}")
    public ResponseVo hotSightOneRemove(@PathVariable("id") Long hotSightOneId) {
        return hotSightOneService.hotSightOneDelete(hotSightOneId);
    }

    @PostMapping("/admin/hotSightOneNameModify/{id}")
    public ResponseVo hotSightOneNameModify(
            @PathVariable("id") Long hotSightOneId,
            @RequestBody HotSightOneNameModifyRequest req) {

        HotSightOneNameModifyDto hotSightOneNameModifyDto = new HotSightOneNameModifyDto(hotSightOneId, req);

        return hotSightOneService.hotSightOneNameAlert(hotSightOneNameModifyDto);
    }

    @PostMapping("/admin/hotSightOneImgModify/{id}")
    public ResponseVo hotSightOneImgModify(
            @PathVariable("id") Long hotSightOneId,
            @RequestPart MultipartFile file) {
        HotSightOneImgModifyDto hotSightOneImgModifyDto = new HotSightOneImgModifyDto(hotSightOneId, file);

        return hotSightOneService.hotSightOneImgAlert(hotSightOneImgModifyDto);
    }

    @GetMapping("/hotSightOneAllList")
    public HotSightOneListVo hotSightOneAllList() {
        return hotSightOneService.hotSightOneAllListSelect();
    }
}
