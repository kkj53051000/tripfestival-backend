package com.tripfestival.service;

import com.tripfestival.domain.HotspotType;
import com.tripfestival.dto.HotspotTypeProcessDto;
import com.tripfestival.exception.HotspotTypeNotFoundException;
import com.tripfestival.repository.HotspotTypeRepository;
import com.tripfestival.request.HotspotTypeProcessRequest;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class HotspotTypeService {
    private final HotspotTypeRepository hotspotTypeRepository;

    private final FileService fileService;

    public ResponseVo hotspotTypeInsert(HotspotTypeProcessDto req) {
        String url = fileService.s3UploadProcess(req.getFile());

        HotspotType hotspotType = HotspotType.builder()
                .name(req.getName())
                .img(url)
                .build();

        hotspotTypeRepository.save(hotspotType);

        return new ResponseVo(Response.SUCCESS, null);
    }

    public ResponseVo hotspotTypeRemove(Long hotspotTypeId) {
        HotspotType hotspotType = hotspotTypeRepository.findById(hotspotTypeId)
                .orElseThrow(() -> new HotspotTypeNotFoundException());

        hotspotTypeRepository.delete(hotspotType);

        return new ResponseVo(Response.SUCCESS, null);
    }
}
