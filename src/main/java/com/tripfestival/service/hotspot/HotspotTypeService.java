package com.tripfestival.service.hotspot;

import com.tripfestival.domain.hotspot.HotspotType;
import com.tripfestival.dto.hotspot.HotspotTypeNameModifyDto;
import com.tripfestival.dto.hotspot.HotspotTypeProcessDto;
import com.tripfestival.exception.hotspot.HotspotTypeNotFoundException;
import com.tripfestival.repository.hotspot.HotspotTypeRepository;
import com.tripfestival.service.file.FileService;
import com.tripfestival.vo.hotspot.HotspotTypeAllListVo;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.hotspot.HotspotTypeNameVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotspotTypeService {
    private final HotspotTypeRepository hotspotTypeRepository;

    private final FileService fileService;

    @Transactional
    public ResponseVo hotspotTypeInsert(HotspotTypeProcessDto req) {
        String url = fileService.s3UploadProcess(req.getFile());

        HotspotType hotspotType = HotspotType.builder()
                .name(req.getName())
                .img(url)
                .build();

        hotspotTypeRepository.save(hotspotType);

        return new ResponseVo(Response.SUCCESS, null);
    }

    @Transactional
    public ResponseVo hotspotTypeDelete(Long hotspotTypeId) {
        HotspotType hotspotType = hotspotTypeRepository.findById(hotspotTypeId)
                .orElseThrow(() -> new HotspotTypeNotFoundException());

        hotspotTypeRepository.delete(hotspotType);

        return new ResponseVo(Response.SUCCESS, null);
    }

    @Transactional
    public ResponseVo hotspotTypeNameAlert(HotspotTypeNameModifyDto req) {
        HotspotType hotspotType = hotspotTypeRepository.findById(req.getHotspotTypeId())
                .orElseThrow(() -> new HotspotTypeNotFoundException());

        hotspotType.setName(req.getName());

        return new ResponseVo(Response.SUCCESS, null);
    }

    @Transactional(readOnly = true)
    public HotspotTypeAllListVo hotspotTypeAllListSelect() {
        List<HotspotType> hotspotTypeList = hotspotTypeRepository.findAll();

        return new HotspotTypeAllListVo(hotspotTypeList);
    }

    @Transactional(readOnly = true)
    public HotspotTypeNameVo HotspotTypeNameSelect(Long hotspotTypeId) {
        HotspotType hotspotType = hotspotTypeRepository.findById(hotspotTypeId)
                .orElseThrow(() -> new HotspotTypeNotFoundException());

        HotspotTypeNameVo hotspotTypeNameVo = HotspotTypeNameVo.builder()
                .hotSpotTypeName(hotspotType.getName())
                .build();

        return hotspotTypeNameVo;
    }
}
